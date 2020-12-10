package com.epam.esm.db.service;

import com.epam.esm.db.data.Order;

import java.util.List;

public interface OrderDAO extends CRUDOperations<Order> {
    List<Order> findByUserId(Long pageSize, Long page, Long userId);
}
