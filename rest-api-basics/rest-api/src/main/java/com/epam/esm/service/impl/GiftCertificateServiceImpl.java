package com.epam.esm.service.impl;

import com.epam.esm.data.GiftCertificateCreateDto;
import com.epam.esm.data.GiftCertificateDto;
import com.epam.esm.db.data.GiftCertificate;
import com.epam.esm.db.data.Tag;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.GiftCertificateDAO;
import com.epam.esm.db.service.TagDAO;
import com.epam.esm.service.GiftCertificatesService;
import com.epam.esm.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class GiftCertificateServiceImpl implements GiftCertificatesService {

    private GiftCertificateDAO giftCertificateDAO;
    private TagDAO tagDAO;

    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, TagDAO tagDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagDAO = tagDAO;
    }

    @Override
    public List<GiftCertificateDto> getAllCertificates() {

        List<GiftCertificate> giftCertificates = giftCertificateDAO.findAll();
        return giftCertificates.stream()
                .map(gc -> new GiftCertificateDto(
                        gc.getId(),
                        gc.getName(),
                        gc.getDescription(),
                        gc.getPrice(),
                        gc.getCreateDate(),
                        gc.getLastUpdateTime(),
                        gc.getDuration(),
                        tagDAO.findAllCertificateTags(gc.getId())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto findById(Long id) throws EntityNotFoundException {

        Optional<GiftCertificate> giftCertificate = Optional.of(giftCertificateDAO.findById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("certificate with id=%s not found", id)));
        return certificateBuilder(giftCertificate.get());
    }

    @Override
    public void createCertificate(GiftCertificateCreateDto giftCertificateCreateDto) throws DAOException {

        GiftCertificate gc = createNewCertificate(giftCertificateCreateDto);
        gc.setId(giftCertificateDAO.createEntity(gc));
        for (Tag tag : giftCertificateCreateDto.getTags()) {
            Optional<Tag> optionalTag = tagDAO.findByName(tag.getName());
            if (optionalTag.isPresent()){
                tag.setId(optionalTag.get().getId());
            }
            else {
                tag.setId(tagDAO.createEntity(tag));
            }
        }
        giftCertificateDAO.addCertificateTags(giftCertificateCreateDto.getTags(), gc.getId());
    }

    public void removeCertificate(GiftCertificateDto giftCertificateDto) {

    }

    private GiftCertificate createNewCertificate(GiftCertificateCreateDto giftCertificateCreateDto) {

        Date currentDate = new Date();
        Timestamp currentDateTimestamp = new Timestamp(currentDate.getTime());

        GiftCertificate gc = new GiftCertificate();
        gc.setName(giftCertificateCreateDto.getName());
        gc.setDescription(giftCertificateCreateDto.getDescription());
        gc.setPrice(giftCertificateCreateDto.getPrice());
        gc.setCreateDate(currentDateTimestamp);
        gc.setLastUpdateTime(currentDateTimestamp);
        gc.setDuration(giftCertificateCreateDto.getDuration());

        return gc;
    }

    private GiftCertificateDto certificateBuilder(GiftCertificate gc) {

        return new GiftCertificateDto(
                gc.getId(),
                gc.getName(),
                gc.getDescription(),
                gc.getPrice(),
                gc.getCreateDate(),
                gc.getLastUpdateTime(),
                gc.getDuration(),
                tagDAO.findAllCertificateTags(gc.getId()));
    }

    private GiftCertificate certificateFromDtoBuilder(GiftCertificateDto gcd) {

        return new GiftCertificate(
                gcd.getId(),
                gcd.getName(),
                gcd.getDescription(),
                gcd.getPrice(),
                gcd.getCreateDate(),
                gcd.getLastUpdateTime(),
                gcd.getDuration()
        );

    }


}
