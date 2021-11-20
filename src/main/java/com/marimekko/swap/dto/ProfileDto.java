package com.marimekko.swap.dto;

import com.marimekko.swap.model.Schedule;
import com.marimekko.swap.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProfileDto {
    private final User user;
    private final List<Schedule> currentItems;
    private final List<Schedule> bookedItems;
}
