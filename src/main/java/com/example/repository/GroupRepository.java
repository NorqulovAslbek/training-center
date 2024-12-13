package com.example.repository;

import com.example.entity.GroupEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Integer> {
    @Query("from GroupEntity as g where g.name=:name and g.visible=true")
    Optional<GroupEntity> getByGroupName(String name);
}
