package com.epam.esm.db.service;



import com.epam.esm.db.data.GiftCertificate;

import java.util.List;

public interface GiftCertificateRepo extends CRUDOperation<GiftCertificate> {

    List query(Specification specification);

}
