package com.marimekko.swap.dto;

import com.marimekko.swap.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ItemAvailabilityDto {
    private final Item item;
    @Setter
    private Instant availableMonth;
}
