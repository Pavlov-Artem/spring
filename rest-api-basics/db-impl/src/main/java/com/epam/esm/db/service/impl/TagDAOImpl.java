package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.Tag;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.TagDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class TagDAOImpl implements TagDAO {

    private static final String TABLE_NAME = "tag";
    private RowMapper<Tag> rowMapper;
    private JdbcTemplate jdbcTemplate;

    public TagDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        setRowMapper();

    }

    @Override
    public Optional<Tag> findByName(String name) {

        String query = String.format("select * from `%s` where name=?", TABLE_NAME);
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, rowMapper));
    }

    @Override
    public List<Tag> findAll() {

        String query = String.format("select * from `%s`", TABLE_NAME);
        return new ArrayList<>(jdbcTemplate.query(query, rowMapper));
    }

    @Override
    public Long createEntity(Tag entity) throws DAOException {

        String query = "insert into " + TABLE_NAME + " (name) values(?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            int i = 0;
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(++i, entity.getName());
            return ps;
        }, keyHolder);

        Optional<Number> optionalKey = Optional.ofNullable(keyHolder.getKey());
        long generatedId = optionalKey.orElseThrow(() -> {
            //LOGGER.info("Employer entity hasn't created in database");
            return new DAOException("ID hasn't generated in database");
        }).longValue();
        entity.setId(generatedId);
        return entity.getId();
    }

    @Override
    public Optional<Tag> findById(Long id) {

        String query = String.format("select * from `%s` where id=`%s`", TABLE_NAME, id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, rowMapper));

    }

    @Override
    public void updateCertificate(Tag entity) {


    }

    @Override
    public void deleteCertificate(Tag entity) throws DAOException {

        String query = String.format("delete from `%s` where id=?", TABLE_NAME);
        int rowsAffected = jdbcTemplate.update(query, entity.getId());
        if (rowsAffected == 0) {
            throw new DAOException(String.format("certificate with id=`%s` wasn't delete", entity.getId()));
        }

    }

    private void setRowMapper() {

        rowMapper = (rs, rowNum) -> {
            int i = 0;
            return new Tag(
                    rs.getLong(++i),
                    rs.getString(++i));
        };
    }

}
