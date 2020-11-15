package com.epam.esm.service.impl;

import com.epam.esm.db.data.Tag;
import com.epam.esm.db.service.DAOException;
import com.epam.esm.db.service.TagDAO;
import com.epam.esm.db.service.exceptions.EntityNotFoundDaoException;
import com.epam.esm.service.TagService;

import java.util.List;
import java.util.Optional;

public class TagServiceImpl implements TagService {


    private TagDAO tagDAO;

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDAO.findAll();
    }

    @Override
    public Tag findTagById(Long id) throws DAOException {

        Optional<Tag> tag = tagDAO.findById(id);
        if (tag.isPresent()) {
            return tag.get();
        } else {
            throw new EntityNotFoundDaoException("tag not found", id);
        }
    }

    @Override
    public void createTag(Tag tag) {

    }

    @Override
    public void removeTag(Long id) {

    }
}
