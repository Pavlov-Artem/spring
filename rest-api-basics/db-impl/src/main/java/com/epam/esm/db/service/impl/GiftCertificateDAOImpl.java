package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.GiftCertificate;
import com.epam.esm.db.data.Tag;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.GiftCertificateDAO;
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
import java.util.Optional;

@Repository
public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private static final String TABLE_NAME = "gift_certificate";
    private RowMapper<GiftCertificate> rowMapper;
    private JdbcTemplate jdbcTemplate;


    public GiftCertificateDAOImpl(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        setRowMapper();
    }

    @Override
    public List<GiftCertificate> findAll() {

        String query = String.format("select * from `%s`", TABLE_NAME);
        return new ArrayList<>(jdbcTemplate.query(query, rowMapper));
    }

    @Override
    public Long createEntity(GiftCertificate entity) throws DAOException {

        String query = "insert into " + TABLE_NAME + " (name,description,price,create_date,last_update_date,duration) values(?,?,?,?,?,?)";

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
    public Optional<GiftCertificate> findById(Long id) {

        String query = String.format("select * from `%s` where id=%s", TABLE_NAME, id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, rowMapper));

    }

    @Override
    public void updateCertificate(GiftCertificate entity) {

        //String query = String.format("update ")
    }

    @Override
    public void deleteCertificate(GiftCertificate entity) throws DAOException {

        String query = String.format("delete from `%s` where id=%s", TABLE_NAME, entity.getId());
        int rowsAffected = jdbcTemplate.update(query, entity.getId());
        if (rowsAffected == 0) {
            throw new DAOException(String.format("certificate with id=`%s` wasn't delete", entity.getId()));
        }
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
        tags.forEach(tag -> jdbcTemplate.update(query, tag.getId(), id));

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
