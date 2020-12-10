package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.Order;
import com.epam.esm.db.data.User;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.OrderDAO;
import com.epam.esm.db.service.exceptions.OrderCreationException;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Transactional
public class OrderDAOImpl implements OrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
    private CommonJpaOperations<Order> commonJpaOperations;

    public OrderDAOImpl(CommonJpaOperations<Order> commonJpaOperations) {
        this.commonJpaOperations = commonJpaOperations;
    }

    @Override
    public List<Order> findAll(Long pageSize, Long page) {
        return Collections.unmodifiableList(commonJpaOperations.findAllBasic(pageSize, page, Order.class));
    }

    @Override
    @Transactional
    public Long createEntity(Order entity) throws DAOException {
        try {
            this.entityManager.persist(entity);
            return (Long) this.entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        } catch (Exception ex) {
            throw new OrderCreationException("error while creating order", entity);
        }

    }

    @Override
    public Optional<Order> findById(Long id) throws DAOException {
        Order order = entityManager.find(Order.class, id);
        return order == null ? Optional.empty() : Optional.of(order);
    }

    @Override
    public void updateEntity(Order entity) throws DAOException {
        throw new UnsupportedOperationException("temporary unavailable", "update order");
    }

    @Override
    @Transactional
    public void deleteEntity(Order entity) throws DAOException {
        try{
            Order order = entityManager.find(Order.class, entity.getId());
            entityManager.remove(order);
        } catch (Exception ex) {
            throw new DAOException(String.format("cannot remove order with id={0}",entity.getId()));
        }
    }

    @Override
    public List<Order> findByUserId(Long pageSize, Long page, Long userId) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        //getting count of all objects in db
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        countQuery
//                .select(criteriaBuilder
//                        .count(countQuery.from(type)));
////        Long count = entityManager
//////                .createQuery(countQuery)
//////                .getSingleResult();
//
//        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
//        Root<T> from = criteriaQuery.from(type);
//        CriteriaQuery<T> select = criteriaQuery.select(from);
//
//        TypedQuery<T> typedQuery = entityManager.createQuery(select);
//        int firstResult = (int) ((page - 1L) * pageSize);
//        typedQuery.setFirstResult(firstResult);
//        typedQuery.setMaxResults(Math.toIntExact(pageSize));
//        return typedQuery.getResultList();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

//        CriteriaQuery<Order> cq = criteriaBuilder.createQuery(Order.class);
//        Root<Order> root = cq.from(Order.class);
//        cq.select(root).where(root.get("userId"))
//        Metamodel m = entityManager.getMetamodel();
//        EntityType<Order> order = m.entity(Order.class);
//        EntityType<User> user = m.entity(User.class);
//        Root<Order> orders = cq.from(Order.class);
//        Join<Order, User> userJoin = orders.join(order.)
        return null;
    }
}
