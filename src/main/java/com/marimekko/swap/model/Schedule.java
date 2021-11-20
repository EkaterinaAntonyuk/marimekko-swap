package com.marimekko.swap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private Instant monthOfUsage;
    @Embedded
    private Location location;

    public Schedule(User user, Item item, Instant monthOfUsage) {
        this.user = user;
        this.item = item;
        this.monthOfUsage = monthOfUsage;
        this.location = item.getLocation();
    }
}
