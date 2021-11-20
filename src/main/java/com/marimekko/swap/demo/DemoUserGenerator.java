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

        var items = generateItems(user);
        generateSchedules(user, items);
        return user.getId();
    }

    private List<Item> generateItems(User user) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Dress1", ItemType.CLOTHING, "M", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress2", ItemType.CLOTHING, "M", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress3", ItemType.CLOTHING, "M", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress4", ItemType.CLOTHING, "S", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress5", ItemType.CLOTHING, "L", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes6", ItemType.ACCESSORIES, "37", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes7", ItemType.ACCESSORIES, "38", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes8", ItemType.ACCESSORIES, "38", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes9", ItemType.ACCESSORIES, "39", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Bag10", ItemType.BAGS, "One size", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress31", ItemType.CLOTHING, "M", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress32", ItemType.CLOTHING, "XS", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Dress33", ItemType.CLOTHING, "M", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Bag11", ItemType.BAGS, "One size", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes12", ItemType.ACCESSORIES, "38", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes13", ItemType.ACCESSORIES, "38", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes14", ItemType.ACCESSORIES, "39", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Bag15", ItemType.BAGS, "One size", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Bag16", ItemType.BAGS, "One size", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes17", ItemType.ACCESSORIES, "38", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes18", ItemType.ACCESSORIES, "38", user.getId(), user.getId() - 1, null, ESPOO_SELLO));
        items.add(new Item("Shoes19", ItemType.ACCESSORIES, "39", user.getId(), user.getId(), null, ESPOO_SELLO));
        items.add(new Item("Bag20", ItemType.BAGS, "One size", user.getId(), user.getId(), null, ESPOO_SELLO));
        items.add(new Item("Bag21", ItemType.BAGS, "One size", user.getId(), user.getId(), null, ESPOO_SELLO));
        itemRepository.saveAll(items);
        return items;
    }

    private List<Schedule> generateSchedules(User user, List<Item> items) {
        final User fake = new User("fake", 10);
        userRepository.save(fake);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(user, items.get(0), monthsFromNow(0)));
        schedules.add(new Schedule(user, items.get(1), monthsFromNow(0)));
        schedules.add(new Schedule(user, items.get(2), monthsFromNow(0)));
        schedules.add(new Schedule(user, items.get(3), monthsFromNow(1)));
        schedules.add(new Schedule(user, items.get(4), monthsFromNow(1)));
        schedules.add(new Schedule(user, items.get(5), monthsFromNow(1)));
        schedules.add(new Schedule(fake, items.get(6), monthsFromNow(0)));
        schedules.add(new Schedule(fake, items.get(7), monthsFromNow(1)));
        schedules.add(new Schedule(fake, items.get(8), monthsFromNow(0)));
        schedules.add(new Schedule(fake, items.get(9), monthsFromNow(1)));
        schedules.add(new Schedule(fake, items.get(10), monthsFromNow(1)));
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
