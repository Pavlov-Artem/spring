package com.epam.esm.rest;

import com.epam.esm.data.GiftCertificateCreateDto;
import com.epam.esm.data.GiftCertificateDto;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.service.GiftCertificatesService;
import com.epam.esm.service.exceptions.EntityNotFoundException;
import com.epam.esm.validation.GiftCertificatesCreateDtoValidator;
import com.epam.esm.validation.ValidationResult;
import com.epam.esm.validation.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftCertificateRestController {

    private final GiftCertificatesService giftCertificatesService;
    private final Validator<GiftCertificateCreateDto> certificateCreationValidator;

    public GiftCertificateRestController(GiftCertificatesService giftCertificatesService) {
        this.giftCertificatesService = giftCertificatesService;

        certificateCreationValidator = new GiftCertificatesCreateDtoValidator();
    }

    @GetMapping("/certificates")
    public List<GiftCertificateDto> all() {
        return giftCertificatesService.getAllCertificates();
    }


    @GetMapping(value = "/certificates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable String id) {

        try {
            Long certificateId = Long.parseLong(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(giftCertificateDtoToJson(giftCertificatesService.findById(certificateId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Certificate with id=%s not found", id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error try again later");
        }

    }


//    @PutMapping("/employees/{id}")
//    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @PutMapping("/certificates")
    public ResponseEntity<String> createCertificate(@RequestBody GiftCertificateCreateDto giftCertificateCreateDto) {

        ValidationResult validationResult = certificateCreationValidator.validate(giftCertificateCreateDto);
        if (validationResult.isValid()) {
            try {
                giftCertificatesService.createCertificate(giftCertificateCreateDto);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Certificate successfully created");

            } catch (DAOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Server error try again later");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("bad request params");

        }

    }

//    @DeleteMapping("/certificates/{id}")
//    public ResponseEntity<String> removeCertificate(@PathVariable String id) {
//
//
//
//    }


    private String giftCertificateDtoToJson(GiftCertificateDto giftCertificateDto) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(giftCertificateDto);

    }


}
