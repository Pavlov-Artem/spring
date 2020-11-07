package com.epam.esm.db.service;

import com.epam.esm.db.data.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDAO extends CRUDOperation<Tag> {

    Optional<Tag> findByName(String name);
    List<Tag> findAllCertificateTags(Long certificateId);


}
