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

    private static final Location SELLO = new Location("Marimekko Sello", 60.21800208685317, 24.81146053664228);
    private static final Location KAMPPI = new Location("Marimekko Kamppi", 60.16911537351664, 24.933823481080115);
    private static final Location TAPIOLA = new Location("Marimekko Tapiola", 60.177189345511415, 24.803440433849186);

    private static final String NO_SIZE = "One size";

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
        items.add(new Item("Peurankello Pieni Unikko 2", ItemType.CLOTHING, "S", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090277-059_X191763_10_1631714895.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));
        items.add(new Item("Lehdot Mini Unikko", ItemType.CLOTHING, "M", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090362-099_X191862_10_1631687848.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));
        items.add(new Item("Kietoa Unikko", ItemType.CLOTHING, "L", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090488-079_X200505_10_1636359482_1.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", TAPIOLA));
        items.add(new Item("Uusi Matkuri Juhlaunikko", ItemType.BAGS, NO_SIZE, user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090322-012_X191902_10_1634193281.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", TAPIOLA));
        items.add(new Item("Basic bag Small Poppy", ItemType.BAGS, NO_SIZE, user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/4/047587-999_X133739_10_1598366643.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", TAPIOLA));
        items.add(new Item("Sue Jacquard Juhla-Unikko", ItemType.ACCESSORIES, NO_SIZE, user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090174-099_X185707_10_1626870630.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", TAPIOLA));
        items.add(new Item("Nurmikolle", ItemType.ACCESSORIES, NO_SIZE, user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090082-364_X185693_10_1623855053.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));
        items.add(new Item("Drutha Mini Poppy", ItemType.ACCESSORIES, "39", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/4/049690-899_X185709_10_1622189091.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", KAMPPI));
        items.add(new Item("Kietoa Unikko", ItemType.CLOTHING, "L", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090488-011_X200504_10_1636359476.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", KAMPPI));
        items.add(new Item("Palokärki", ItemType.CLOTHING, "XS", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090276-003_X191680_10_1634734283.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));
        items.add(new Item("Hiljainen Tasaraita Light", ItemType.CLOTHING, "S", user.getId(), user.getId() - 1, "https://www.marimekko.com/media/catalog/product/0/9/090244-838_X191661_10_1631715502.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", TAPIOLA));
        items.add(new Item("Mini Pixie", ItemType.BAGS, NO_SIZE, user.getId(), user.getId(), "https://www.marimekko.com/media/catalog/product/0/4/048366-009_X151874_10_1602154632.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));
        items.add(new Item("Pitkähiha", ItemType.CLOTHING, "M", user.getId(), user.getId(), "https://www.marimekko.com/media/catalog/product/0/9/090392-838_X191727_10_1632899854.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));
        items.add(new Item("Lae Tasaraita Light", ItemType.CLOTHING, "M", user.getId(), user.getId(), "https://www.marimekko.com/media/catalog/product/0/9/090222-838_X191722_10_1631715903.jpg?width=1920&height=2400&canvas=1920:2400&quality=100&bg-color=255,255,255&fit=bounds", SELLO));

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
        schedules.add(new Schedule(fake, items.get(5), monthsFromNow(1)));
        schedules.add(new Schedule(fake, items.get(6), monthsFromNow(1)));
        schedules.add(new Schedule(fake, items.get(7), monthsFromNow(1)));
        schedules.add(new Schedule(fake, items.get(8), monthsFromNow(2)));
        schedules.add(new Schedule(fake, items.get(9), monthsFromNow(2)));
        schedules.add(new Schedule(fake, items.get(10), monthsFromNow(3)));
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
