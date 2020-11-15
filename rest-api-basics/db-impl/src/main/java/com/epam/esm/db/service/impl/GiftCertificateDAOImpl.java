package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.GiftCertificate;
import com.epam.esm.db.data.Tag;
import com.epam.esm.db.service.CertificateSearchCriteria;
import com.epam.esm.db.service.CertificateSortCriteria;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.GiftCertificateDAO;
import com.epam.esm.db.service.exceptions.EntityNotFoundDaoException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.db.service.impl.DAOConstants.CERTIFICATE_TABLE;

@Repository
public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private RowMapper<GiftCertificate> rowMapper;
    private JdbcTemplate jdbcTemplate;

    public GiftCertificateDAOImpl(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        setRowMapper();
    }

    @Override
    public List<GiftCertificate> findAll() {

        String query = String.format("select * from `%s`", CERTIFICATE_TABLE);
        return new ArrayList<>(jdbcTemplate.query(query, rowMapper));
    }


    @Override
    public List<GiftCertificate> findAllWithCriteria(Map<CertificateSearchCriteria, String> searchCriteria, List<CertificateSortCriteria> sortCriteria) {

        String query = QueryBuilder.buildCompoundQuery(searchCriteria, sortCriteria);
        return new ArrayList<>(jdbcTemplate.query(query, rowMapper));
    }


    @Override
    public Long createEntity(GiftCertificate entity) throws DAOException {

        String query = "insert into " + CERTIFICATE_TABLE + " (name,description,price,create_date,last_update_date,duration) values(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            int i = 0;
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(++i, entity.getName());
            ps.setString(++i, entity.getDescription());
            ps.setBigDecimal(++i, entity.getPrice());
            ps.setTimestamp(++i, entity.getCreateDate());
            ps.setTimestamp(++i, entity.getLastUpdateTime());
            ps.setInt(++i, entity.getDuration());
            return ps;
        }, keyHolder);

        Optional<Number> optionalKey = Optional.ofNullable(keyHolder.getKey());
        long generatedId = optionalKey.orElseThrow(() -> new DAOException("ID hasn't generated in database")).longValue();
        entity.setId(generatedId);
        return entity.getId();

    }

    @Override
    public Optional<GiftCertificate> findById(Long id) throws DAOException {

        String query = String.format("select * from `%s` where id=%s", CERTIFICATE_TABLE, id);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, rowMapper));
        } catch (Exception ex) {
            throw new EntityNotFoundDaoException("certificate not found ", id);
        }

    }

    @Override
    public void updateCertificate(GiftCertificate entity) throws DAOException {

        GiftCertificate oldCertificate = findById(entity.getId()).get();
        String query = QueryBuilder.updateChangedCertificateRowsBuilder(oldCertificate, entity);
        if (jdbcTemplate.update(query) < 1) {
            throw new DAOException("certificate wasn't update");
        }
    }

    @Override
    public void updateCertificateTags(Long certificateId, List<Tag> tags) {

        String selectOldTags = "select tag_id from gift_certificate_has_tag where gift_certificate_id=?";
        List<Long> oldTags = jdbcTemplate.query(selectOldTags, (rs, rowNum) -> rs.getLong(1), certificateId);
        List<Long> newTags = tags.stream().map(Tag::getId).collect(Collectors.toList());

        //remove old tags for certificate
        String removeOldTag = "delete from gift_certificate_has_tag where tag_id=?";
        for (Long tagId : oldTags) {
            if (!newTags.contains(tagId)) {
                jdbcTemplate.update(removeOldTag, tagId);
            }
        }
        //add new tags for certificate
        String insertNewTag = "insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (?,?)";
        for (Long tagId : newTags) {
            if (!oldTags.contains(tagId)) {
                jdbcTemplate.update(insertNewTag, certificateId, tagId);
            }
        }
    }

    @Override
    public void deleteCertificate(GiftCertificate entity) throws DAOException {

        String query = String.format("delete from `%s` where id=?", CERTIFICATE_TABLE);
        int rowsAffected = jdbcTemplate.update(query, entity.getId());
        if (rowsAffected == 0) {
            throw new DAOException(String.format("certificate with id=`%s` wasn't delete", entity.getId()));
        }
        removeCertificateTags(entity.getId());
    }

    private void removeCertificateTags(Long certificateId) {

        String query = "delete from gift_certificate_has_tag where gift_certificate_id=?";
        jdbcTemplate.update(query, certificateId);

    }

    @Override
    public List<GiftCertificate> findCertificatesByTagName(String tagName) {

        String query = "select a.* from gift_certificate a  " +
                "                join gift_certificate_has_tag gt  " +
                "                on gt.gift_certificate_id = a.id  " +
                "                join tag t " +
                "                on t.id = gt.tag_id " +
                "                WHERE t.name IN (?)";

        return new ArrayList<>(jdbcTemplate.query(query, rowMapper, tagName));

    }

    @Override
    public void addCertificateTags(List<Tag> tags, Long id) {

        String query = "insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (?,?);";
        tags.forEach(tag -> jdbcTemplate.update(query, id, tag.getId()));

    }

    private void setRowMapper() {

        rowMapper = (rs, rowNum) -> {
            int i = 0;
            return new GiftCertificate(
                    rs.getLong(++i),
                    rs.getString(++i),
                    rs.getString(++i),
                    rs.getBigDecimal(++i),
                    rs.getTimestamp(++i),
                    rs.getTimestamp(++i),
                    rs.getInt(++i));
        };
    }


}
