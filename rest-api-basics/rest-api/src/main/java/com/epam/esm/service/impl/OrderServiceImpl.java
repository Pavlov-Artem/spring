package com.epam.esm.service.impl;

import com.epam.esm.db.data.Order;
import com.epam.esm.db.service.OrderDAO;
import com.epam.esm.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getAll(Long pageSize, Long page) {
        return orderDAO.findAll(pageSize, page);
    }
}
