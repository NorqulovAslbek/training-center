package com.example.repository;

import com.example.dto.PaginationResultDTO;
import com.example.dto.ProfileRequestAndResponseDTO;
import com.example.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProfileFilterRepository {

    private final EntityManager entityManager;

    public PaginationResultDTO<ProfileEntity> filter(ProfileRequestAndResponseDTO filter, Integer page, Integer size) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        // name va surname phone va role boyicha filterlasam yetadi manga

        if (filter.getPhone() != null) {
            builder.append(" and phone=:phone ");
            params.put("phone", filter.getPhone());
        }
        if (filter.getName() != null) {
            builder.append(" and lower(name) like :name ");
            params.put("name", "%" + filter.getName().toLowerCase() + "%");
        }
        if (filter.getSurname() != null) {
            builder.append(" and lower(surname) like :surname ");
            params.put("surname", "%" + filter.getSurname() + "%");
        }
        if (filter.getRole() != null) {
            builder.append(" and role=:role ");
            params.put("role", filter.getRole());
        }

        String selectBuilder = "From ProfileEntity p where 1=1 " + builder + " order by createdDate desc";

        String countBuilder = "Select count(p) from ProfileEntity as p where 1=1 " + builder;

        Query selectQuery = entityManager.createQuery(selectBuilder);
        Query countQuery = entityManager.createQuery(countBuilder);

        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult((page - 1) * size);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        List<ProfileEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PaginationResultDTO<>(entityList, totalElements);

    }


}
