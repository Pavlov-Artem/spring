package com.epam.esm.rest;


import com.epam.esm.db.data.Tag;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagRestController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAll(){

        return ResponseEntity.status(HttpStatus.OK).body(tagService.getAllTags());

    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getById(@PathVariable Long id) throws DAOException {

        return ResponseEntity.status(HttpStatus.OK).body(tagService.findTagById(id));

    }


}
