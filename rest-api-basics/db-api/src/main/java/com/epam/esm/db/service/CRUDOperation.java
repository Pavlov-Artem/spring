package com.epam.esm.db.service;

import com.epam.esm.db.data.Tag;

import java.util.List;
import java.util.Optional;

/**
 * @param <T> entity type
 */
public interface CRUDOperation<T> {

    List<T> findAll();

    Long createEntity(T entity) throws DAOException;

    Optional<T> findById(Long id);

    void updateCertificate(T entity);

    void deleteCertificate(T entity) throws DAOException;


}
