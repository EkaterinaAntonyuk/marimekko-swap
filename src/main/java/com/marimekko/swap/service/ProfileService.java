package com.marimekko.swap.service;

import com.marimekko.swap.dto.ProfileDto;
import com.marimekko.swap.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDto getProfile(Integer userId){
        return new ProfileDto(profileRepository.getUser(userId),
                profileRepository.getUserItems(userId));

    }

}
