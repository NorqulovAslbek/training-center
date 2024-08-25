package com.example.service;

import com.example.dto.PaginationResultDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.ProfileRequestAndResponseDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadException;
import com.example.repository.ProfileFilterRepository;
import com.example.repository.ProfileRepository;
import com.example.util.MDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileFilterRepository profileFilterRepository;

    public ProfileRequestAndResponseDTO create(ProfileDTO proFileDTO) {
        ProfileEntity entity = getProfileEntity(proFileDTO);
        ProfileEntity profileEntity = profileRepository.save(entity);
        return getToDTO(profileEntity);
    }


    public ProfileRequestAndResponseDTO update(Integer id, ProfileRequestAndResponseDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.profileById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("not found profile");
        }
        ProfileEntity profileEntity = optional.get();
        profileEntity.setName(dto.getName() != null ? dto.getName() : profileEntity.getName());
        profileEntity.setSurname(dto.getSurname() != null ? dto.getSurname() : profileEntity.getSurname());
        profileEntity.setPhone(dto.getPhone() != null ? dto.getPhone() : profileEntity.getPhone());
        profileEntity.setPassword(MDUtil.encode(dto.getPassword() != null ? dto.getPassword() : profileEntity.getPassword()));
        profileEntity.setRole(dto.getRole() != null ? dto.getRole() : profileEntity.getRole());
        profileEntity.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(profileEntity);
        return getToDTO(profileEntity);
    }

    public boolean delete(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.profileById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("no such user exists!");
        }

        ProfileEntity profileEntity = optional.get();
        profileEntity.setVisible(false);
        profileRepository.save(profileEntity);
        return true;
    }

    public List<ProfileRequestAndResponseDTO> getList() {
        List<ProfileEntity> all = profileRepository.getByProfileList();
        if (all.isEmpty()){
            throw new AppBadException("profile not found");
        }
        List<ProfileRequestAndResponseDTO> profileList = new LinkedList<>();
        for (ProfileEntity profileEntity : all) {
            profileList.add(getToDTO(profileEntity));
        }
        return profileList;
    }


    private ProfileRequestAndResponseDTO getToDTO(ProfileEntity profileEntity) {
        ProfileRequestAndResponseDTO profileResponseDTO = new ProfileRequestAndResponseDTO();
        profileResponseDTO.setId(profileEntity.getId());
        profileResponseDTO.setName(profileEntity.getName());
        profileResponseDTO.setSurname(profileEntity.getSurname());
        profileResponseDTO.setRole(profileEntity.getRole());
        profileResponseDTO.setPhone(profileEntity.getPhone());
        return profileResponseDTO;
    }

    private ProfileEntity getProfileEntity(ProfileDTO proFileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(proFileDTO.getName());
        entity.setPhone(proFileDTO.getPhone());
        entity.setSurname(proFileDTO.getSurname());
        entity.setPassword(MDUtil.encode(proFileDTO.getPassword()));
        entity.setRole(proFileDTO.getRole());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        if (proFileDTO.getPhoto() != null) {
            entity.setPhotoId(proFileDTO.getPhoto());
        }
        return entity;
    }


    public PageImpl<ProfileRequestAndResponseDTO> filter(ProfileRequestAndResponseDTO dto, Integer page, Integer size) {
        PaginationResultDTO<ProfileEntity> filter = profileFilterRepository.filter(dto, page, size);
        List<ProfileRequestAndResponseDTO> list = new LinkedList<>();
        for (ProfileEntity studentEntity : filter.getList()) {
            list.add(getToDTO(studentEntity));
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "createdDate");
        return new PageImpl<>(list, pageable, filter.getTotalSize());
    }
}
