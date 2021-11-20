package com.marimekko.swap.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Location {
    private String name;
    private Double lat;
    private Double lng;
}
