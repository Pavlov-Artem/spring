package com.epam.esm.rest;

import com.epam.esm.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/record")
public class MentoringRestController {

    @Autowired
    private RestService restService;

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public RecordBean getRecord(@PathVariable("id") String id) {
//        return restService.returnBeanById(id);
//    }
}
