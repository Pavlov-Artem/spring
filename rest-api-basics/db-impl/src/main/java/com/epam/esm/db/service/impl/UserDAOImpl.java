package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.User;
import com.epam.esm.db.service.CRUDOperations;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.UserDAO;
import com.epam.esm.db.service.exceptions.UnsupportedOperationException;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(Long pageSize, Long page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //getting count of all objects in db
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery
                .select(criteriaBuilder
                        .count(countQuery.from(User.class)));
        Long count = entityManager
                .createQuery(countQuery)
                .getSingleResult();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);

        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        while (page < count) {
            typedQuery.setFirstResult((int) (page - 1L));
            typedQuery.setMaxResults(Math.toIntExact(pageSize));
            page += pageSize;
        }
        return typedQuery.getResultList();
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
