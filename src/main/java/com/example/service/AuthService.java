package com.example.service;

import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.exp.AppBadException;
import com.example.repository.ProfileRepository;
import com.example.util.JWTUtil;
import com.example.util.MDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileRepository profileRepository;

    public ProfileDTO login(AuthDTO authDTO) {
        Optional<ProfileEntity> optional = profileRepository.login(authDTO.getPhone(), MDUtil.encode(authDTO.getPassword()));
        if (optional.isEmpty()) {
            throw new AppBadException("profile not found");
        }
        ProfileEntity profileEntity = optional.get();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(profileEntity.getName());
        profileDTO.setSurname(profileEntity.getSurname());
        profileDTO.setRole(profileEntity.getRole());
        profileDTO.setJwt(JWTUtil.encode(profileEntity.getPhone(), profileEntity.getRole()));
        return profileDTO;
    }
}
