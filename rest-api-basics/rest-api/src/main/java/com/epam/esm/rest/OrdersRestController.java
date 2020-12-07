package com.epam.esm.rest;

import com.epam.esm.db.data.Order;
import com.epam.esm.db.data.User;
import com.epam.esm.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.certpath.OCSPResponse;

import java.util.List;

@RestController
public class OrdersRestController {
    private OrderService orderService;

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders", params = {"page", "size"})
    public ResponseEntity<List<Order>> getAll(@RequestParam("size") Long pageSize, @RequestParam("page") Long page){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll(pageSize, page));
    }
}
