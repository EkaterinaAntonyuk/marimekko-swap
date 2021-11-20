package com.marimekko.swap.service;

import com.marimekko.swap.dto.ProfileDto;
import com.marimekko.swap.model.Schedule;
import com.marimekko.swap.model.User;
import com.marimekko.swap.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDto getProfile(Integer userId){
        final User user = profileRepository.getUser(userId);
        final List<Schedule> userItems = profileRepository.getUserItems(userId);
        final List<Schedule> currentItems = userItems.stream()
                .filter(schedule -> {
                    final Instant monthOfUsage = schedule.getMonthOfUsage();
                    final YearMonth yearMonth = YearMonth.from(monthOfUsage.atZone(ZoneId.systemDefault()));
                    return yearMonth.equals(YearMonth.now());
                }).collect(Collectors.toList());
        final List<Schedule> bookedItems = userItems.stream()
                .filter(schedule -> {
                    final Instant monthOfUsage = schedule.getMonthOfUsage();
                    final YearMonth yearMonth = YearMonth.from(monthOfUsage.atZone(ZoneId.systemDefault()));
                    return yearMonth.isAfter(YearMonth.now());
                }).collect(Collectors.toList());
        return new ProfileDto(user, currentItems, bookedItems);

    }

}
