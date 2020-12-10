package com.epam.esm.db.service.exceptions;

import com.epam.esm.db.service.DAOException;

public class OrderNotFoundException extends DAOException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
