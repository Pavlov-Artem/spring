package com.epam.esm.db.service.exceptions;

import com.epam.esm.db.data.Order;
import com.epam.esm.db.service.DAOException;

public class OrderCreationException extends DAOException {

    private final Order notCreatedOrder;

    public OrderCreationException(String message, Order notCreatedOrder) {
        super(message);
        this.notCreatedOrder = notCreatedOrder;
    }

    public OrderCreationException(String message, Throwable cause, Order notCreatedOrder) {
        super(message, cause);
        this.notCreatedOrder = notCreatedOrder;
    }

    public Order getNotCreatedOrder() {
        return notCreatedOrder;
    }
}
