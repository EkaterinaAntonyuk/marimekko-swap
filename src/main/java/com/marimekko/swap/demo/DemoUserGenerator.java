package com.marimekko.swap.demo;

import com.marimekko.swap.model.*;
import com.marimekko.swap.repository.ItemRepository;
import com.marimekko.swap.repository.ScheduleRepository;
import com.marimekko.swap.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoUserGenerator {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ScheduleRepository scheduleRepository;

    private static final Location ESPOO_SELLO = new Location("Sello", 60.2182139, 24.8108556);

    public DemoUserGenerator(UserRepository userRepository, ItemRepository itemRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public Long generateDemoUser() {
        var user = userRepository.save(new User(
                "[Your name]",
                3
        ));

        var items = generateItems();
        generateSchedules(user, items);
        return user.getId();
    }

    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Dress", ItemType.CLOTHING, "M"));
        items.add(new Item("Dress", ItemType.CLOTHING, "M"));
        items.add(new Item("Dress", ItemType.CLOTHING, "M"));
        items.add(new Item("Dress", ItemType.CLOTHING, "S"));
        items.add(new Item("Dress", ItemType.CLOTHING, "L"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "37"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "38"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "38"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "39"));
        items.add(new Item("Bag", ItemType.BAGS, "One size"));
        items.add(new Item("Bag", ItemType.BAGS, "One size"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "38"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "38"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "39"));
        items.add(new Item("Bag", ItemType.BAGS, "One size"));
        items.add(new Item("Bag", ItemType.BAGS, "One size"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "38"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "38"));
        items.add(new Item("Shoes", ItemType.ACCESSORIES, "39"));
        items.add(new Item("Bag", ItemType.BAGS, "One size"));
        items.add(new Item("Bag", ItemType.BAGS, "One size"));
        itemRepository.saveAll(items);
        return items;
    }

    private List<Schedule> generateSchedules(User user, List<Item> items) {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(user, items.get(0), monthsFromNow(0), ESPOO_SELLO));
        schedules.add(new Schedule(user, items.get(1), monthsFromNow(0), ESPOO_SELLO));
        schedules.add(new Schedule(user, items.get(2), monthsFromNow(0), ESPOO_SELLO));
        schedules.add(new Schedule(user, items.get(3), monthsFromNow(1), ESPOO_SELLO));
        schedules.add(new Schedule(user, items.get(4), monthsFromNow(1), ESPOO_SELLO));
        schedules.add(new Schedule(user, items.get(5), monthsFromNow(2), ESPOO_SELLO));
        scheduleRepository.saveAll(schedules);
        return schedules;
    }

    private Instant monthsFromNow(int month) {
        return Instant.now().atZone(ZoneId.systemDefault()).plus(month, java.time.temporal.ChronoUnit.MONTHS).toInstant();
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
