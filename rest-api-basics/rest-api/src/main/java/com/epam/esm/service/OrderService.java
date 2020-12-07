package com.epam.esm.service;

import com.epam.esm.db.data.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll(Long pageSize, Long page);

}
