package com.epam.esm.service;

import com.epam.esm.data.GiftCertificateCreateDto;
import com.epam.esm.data.GiftCertificateDto;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GiftCertificatesService {

    List<GiftCertificateDto> getAllCertificates();

    GiftCertificateDto findById(Long id) throws EntityNotFoundException;

    void createCertificate(GiftCertificateCreateDto giftCertificateDto) throws DAOException;

    void removeCertificate(GiftCertificateDto giftCertificateDto);



}
