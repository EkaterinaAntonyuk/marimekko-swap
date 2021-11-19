package com.marimekko.swap.model;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class Schedule {
    private final User user;
    private final Item item;
    private final Instant monthOfUsage;
    private final Location location;
}
