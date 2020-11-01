package com.epam.esm.service;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImpl implements RestService {

//    private RecordEntityRepo recordEntityRepo;
//
//    @Autowired
//    public RestServiceImpl(RecordEntityRepo recordEntityRepo) {
//        this.recordEntityRepo = recordEntityRepo;
//    }
//
//    @Override
//    public RecordBean returnBeanById(String id) {
//        if (StringUtils.isBlank(id)) {
//            return null;
//        }
//        return Optional.ofNullable(recordEntityRepo.getRecordEntityById(id))
//            .map(r -> {
//                RecordBean bean = new RecordBean();
//                bean.setId(r.getId());
//                bean.setDescription(r.getName());
//                return bean;
//            })
//            .orElse(null);
//    }
}
