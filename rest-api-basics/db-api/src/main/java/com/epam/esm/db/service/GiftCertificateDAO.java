package com.epam.esm.db.service;

import com.epam.esm.db.data.GiftCertificate;
import com.epam.esm.db.data.Tag;

import java.util.List;
import java.util.Map;

public interface GiftCertificateDAO extends CRUDOperation<GiftCertificate> {

    List<GiftCertificate> findAll();

    List<GiftCertificate> findCertificatesByTagName(String tagName);

    List<GiftCertificate> findAllWithCriteria(Map<CertificateSearchCriteria, String> searchCriteria, List<CertificateSortCriteria> sortCriteria);

    void updateCertificateTags(Long certificateId, List<Tag> tagIds);

    void addCertificateTags(List<Tag> tags, Long id);
}
