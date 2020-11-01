package com.epam.esm.db.service;

import com.epam.esm.db.data.GiftCertificate;

import java.util.List;

public interface GiftCertificateDAO extends CRUDOperation<GiftCertificate> {

    List<GiftCertificate> findAll();
    List<GiftCertificate> findCertificatesByTagName(String tagName);
}
