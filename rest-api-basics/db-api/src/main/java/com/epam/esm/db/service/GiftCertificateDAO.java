package com.epam.esm.db.service;

import com.epam.esm.db.data.GiftCertificate;
import com.epam.esm.db.data.Tag;

import java.util.List;

public interface GiftCertificateDAO extends CRUDOperation<GiftCertificate> {

    List<GiftCertificate> findAll();
    List<GiftCertificate> findCertificatesByTagName(String tagName);

    void addCertificateTags(List<Tag> tags, Long id);
}
