package com.marimekko.swap.dto;

import com.marimekko.swap.model.Item;
import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class ItemAvailabilityDto {
    private final Item item;
    private final Instant availableMonth;
}
