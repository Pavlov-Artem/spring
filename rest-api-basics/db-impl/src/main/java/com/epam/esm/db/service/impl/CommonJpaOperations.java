package com.epam.esm.db.service.impl;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class CommonJpaOperations<T> {

    private EntityManager entityManager;


    public CommonJpaOperations(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /**
     * basic find all entity realization
     * with pagination
     *
     * @param pageSize size on 1 page
     * @param page     page number
     * @return list of entities
     */
    public List<T> findAllBasic(Long pageSize, Long page, Class<T> type) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //getting count of all objects in db
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery
                .select(criteriaBuilder
                        .count(countQuery.from(type)));
//        Long count = entityManager
////                .createQuery(countQuery)
////                .getSingleResult();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> from = criteriaQuery.from(type);
        CriteriaQuery<T> select = criteriaQuery.select(from);

        TypedQuery<T> typedQuery = entityManager.createQuery(select);
        int firstResult = (int) ((page - 1L) * pageSize);
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(Math.toIntExact(pageSize));
        return typedQuery.getResultList();
    }

}
