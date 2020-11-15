package com.epam.esm.db.service.exceptions;

import com.epam.esm.db.service.DAOException;

public class EntityNotFoundDaoException extends DAOException {

    private final Long id;

    public EntityNotFoundDaoException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public EntityNotFoundDaoException(String message, Throwable cause, Long id) {
        super(message, cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
