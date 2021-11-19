package com.marimekko.swap.dto;

import com.marimekko.swap.model.Schedule;
import com.marimekko.swap.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProfileDto {
    private final User user;
    private final List<Schedule> items;
}
