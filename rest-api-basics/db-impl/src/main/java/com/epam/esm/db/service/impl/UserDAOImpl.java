package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.User;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.UserDAO;
import com.epam.esm.db.service.exceptions.UnsupportedOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
@Primary
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    private final CommonJpaOperations<User> commonJpaOperations;

    public UserDAOImpl(CommonJpaOperations<User> commonJpaOperations) {
        this.commonJpaOperations = commonJpaOperations;
    }

    @Override
    public List<User> findAll(Long pageSize, Long page) {
        return commonJpaOperations.findAllBasic(pageSize, page, User.class);
    }

    /**
     * Temporary unsupported operation
     *
     * @param entity
     * @return
     * @throws DAOException
     */
    @Override
    public Long createEntity(User entity) throws DAOException {
        throw new UnsupportedOperationException("operation unavailable", "create user");
    }

    @Override
    public Optional<User> findById(Long id) throws DAOException {
        User user = entityManager.find(User.class, id);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    /**
     * Temporary unsupported operation
     *
     * @param entity
     * @throws DAOException
     */
    @Override
    public void updateEntity(User entity) throws DAOException {
        throw new UnsupportedOperationException("operation unavailable", "update user");
    }

    /**
     * Temporary unsupported operation
     *
     * @param entity
     * @throws DAOException
     */
    @Override
    public void deleteEntity(User entity) throws DAOException {
        throw new UnsupportedOperationException("operation unavailable", "delete user");
    }
}
