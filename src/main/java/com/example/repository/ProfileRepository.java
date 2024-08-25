package com.example.repository;

import com.example.dto.ProfileRequestAndResponseDTO;
import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByPhone(String name);


    @Query(value = "SELECT * FROM profile WHERE phone = ?1 AND password = ?2 AND visible = true AND status = 'ACTIVE'", nativeQuery = true)
    Optional<ProfileEntity> login(String phone, String password);

    @Query(value = "SELECT * FROM profile WHERE id = ?1 AND visible = true AND status = 'ACTIVE'", nativeQuery = true)
    Optional<ProfileEntity> profileById(Integer id);

    @Query("FROM ProfileEntity AS P WHERE P.role <>'ROLE_ADMIN'")
    List<ProfileEntity> getByProfileList();
}
