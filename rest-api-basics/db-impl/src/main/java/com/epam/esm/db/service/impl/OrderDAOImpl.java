package com.epam.esm.db.service.impl;

import com.epam.esm.db.data.Order;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.OrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
        List<Order> orders = commonJpaOperations.findAllBasic(pageSize, page, Order.class);
        LOGGER.info(commonJpaOperations.findAllBasic(pageSize, page, Order.class));
        return orders;
    }

    @Override
    public Long createEntity(Order entity) throws DAOException {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public void updateEntity(Order entity) throws DAOException {

    }

    @Override
    public void deleteEntity(Order entity) throws DAOException {

    }
}
