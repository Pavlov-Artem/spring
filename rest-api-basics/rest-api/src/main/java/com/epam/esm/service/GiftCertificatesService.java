package com.epam.esm.service;

import com.epam.esm.data.GiftCertificateCreateDto;
import com.epam.esm.data.GiftCertificateDto;
import com.epam.esm.db.service.CertificateSearchCriteria;
import com.epam.esm.db.service.CertificateSortCriteria;
import com.epam.esm.db.service.DAOException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface GiftCertificatesService {

    List<GiftCertificateDto> getAllCertificates();

    List<GiftCertificateDto> findCertificatesByCriteria(Map<CertificateSearchCriteria, String> criteriaMap, List<CertificateSortCriteria> sortCriteria);

    GiftCertificateDto findById(Long id) throws DAOException;

    void createCertificate(GiftCertificateCreateDto giftCertificateDto) throws DAOException;

    void removeCertificate(Long id) throws DAOException;

    void updateCertificate(GiftCertificateCreateDto giftCertificateCreateDto, Long id) throws DAOException;


}
