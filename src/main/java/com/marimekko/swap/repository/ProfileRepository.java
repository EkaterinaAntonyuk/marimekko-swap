package com.marimekko.swap.repository;

import com.marimekko.swap.model.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfileRepository {
    public User getUser(Integer id){
        return new User(1, "Nikita Verkhovin", 3);
    }

    public List<Schedule> getUserItems(Integer id){
        User user = new User(1, "Nikita Verkhovin", 3);
        List<Schedule> items = new ArrayList<>();
        items.add(new Schedule(user,
                new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2021-11-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));
        items.add(new Schedule(user,
                new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2021-12-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));
        items.add(new Schedule(user,
                new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2022-01-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));
        items.add(new Schedule(user,
                new Item("Shoes", ItemType.ACCESSORIES, "38"),
                Instant.parse("2021-11-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));
        items.add(new Schedule(user,
                new Item("Shoes", ItemType.ACCESSORIES, "38"),
                Instant.parse("2021-12-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));
        items.add(new Schedule(user,
                new Item("Bag", ItemType.BAGS, "One size"),
                Instant.parse("2021-11-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));
        items.add(new Schedule(user,
                new Item("Bag", ItemType.BAGS, "One size"),
                Instant.parse("2021-11-23T17:14:00.092812Z"),
                new Location("Sello", 60.2182139, 24.8108556)));

        return items;
    }
}
