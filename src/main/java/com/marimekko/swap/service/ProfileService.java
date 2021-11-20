package com.marimekko.swap.service;

import com.marimekko.swap.dto.ProfileDto;
import com.marimekko.swap.model.Schedule;
import com.marimekko.swap.model.User;
import com.marimekko.swap.repository.ScheduleRepository;
import com.marimekko.swap.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.marimekko.swap.utils.MonthUtils.monthStart;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;


    public ProfileService(UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public ProfileDto getProfile(Long userId) {
        final User user = userRepository.findById(userId).orElseThrow();
        final List<Schedule> userSchedules = scheduleRepository.findByUserAndMonthOfUsageAfter(user, monthStart(Instant.now()));

        final List<Schedule> currentItems = userSchedules.stream()
                .filter(schedule -> {
                    final Instant monthOfUsage = schedule.getMonthOfUsage();
                    final YearMonth yearMonth = YearMonth.from(monthOfUsage.atZone(ZoneId.of("UTC")));
                    return yearMonth.equals(YearMonth.now());
                }).collect(Collectors.toList());
        final List<Schedule> bookedItems = userSchedules.stream()
                .filter(schedule -> {
                    final Instant monthOfUsage = schedule.getMonthOfUsage();
                    final YearMonth yearMonth = YearMonth.from(monthOfUsage.atZone(ZoneId.of("UTC")));
                    return yearMonth.isAfter(YearMonth.now());
                }).sorted(Comparator.comparing(Schedule::getMonthOfUsage)).collect(Collectors.toList());
        return new ProfileDto(user, currentItems, bookedItems);
    }
}
