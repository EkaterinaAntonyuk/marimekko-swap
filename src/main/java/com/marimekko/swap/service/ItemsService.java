package com.marimekko.swap.service;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.*;
import com.marimekko.swap.repository.ItemRepository;
import com.marimekko.swap.repository.ScheduleRepository;
import com.marimekko.swap.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.marimekko.swap.utils.MonthUtils.getMonthsBetweenInstants;
import static com.marimekko.swap.utils.MonthUtils.plusMonth;

@Service
public class ItemsService {
    private final ItemRepository itemRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ItemsService(ItemRepository itemRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public List<ItemAvailabilityDto> getAllItems(Long demoUserId) {
        final List<ItemAvailabilityDto> availableItems = getAvailableItmes(demoUserId);
        return availableItems.stream()
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    public List<ItemAvailabilityDto> getFilteredItems(Long demoUserId, ItemType itemType, List<String> sizes, Boolean isAvailable) {
        final List<ItemAvailabilityDto> availableItems = getAvailableItmes(demoUserId);
        return availableItems.stream()
                .filter(itemAvailabilityDto -> {
                    final Item item = itemAvailabilityDto.getItem();
                    var include = true;
                    if (itemType != null) {
                        include = item.getItemType().equals(itemType);
                    }
                    if (include && sizes != null && sizes.size() > 0) {
                        include = sizes.contains(item.getItemSize());
                    }
                    if (include && isAvailable) {
                        include = getMonthsBetweenInstants(itemAvailabilityDto.getAvailableMonth(), Instant.now()) == 1;
                    }
                    return include;
                })
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    private List<ItemAvailabilityDto> getAvailableItmes(Long demoUserId) {
        final List<ItemAvailabilityDto> availableItems = itemRepository.getAvailableItems(demoUserId);
        availableItems.forEach(item -> {
            if (item.getAvailableMonth() == null) {
                item.setAvailableMonth(plusMonth(Instant.now(), 1));
            } else {
                item.setAvailableMonth(plusMonth(item.getAvailableMonth(),1));
            }
        });
        return availableItems;
    }

    public List<Item> getOwnItems(Long userId) {
        return itemRepository.findAllByOwnerId(userId);
    }

    public void bookItem(Long userId, Long itemId) {
        final Item item = itemRepository.findById(itemId).orElseThrow();
        final User user = userRepository.findById(userId).orElseThrow();

        final Optional<Schedule> first = scheduleRepository.getAllByItemAndMonthOfUsageAfter(item, Instant.now()).stream()
                .max(Comparator.comparing(Schedule::getMonthOfUsage));
        Schedule schedule;
        if (first.isEmpty()) {
            schedule = new Schedule(user, item,
                    Instant.now().atZone(ZoneId.of("UTC")).plus(1, ChronoUnit.MONTHS).toInstant());
        } else {
            schedule = new Schedule(user, item,
                    first.get().getMonthOfUsage().atZone(ZoneId.of("UTC")).plus(1, ChronoUnit.MONTHS).toInstant());
        }


        final List<Schedule> userBookings = scheduleRepository.findByUserAndMonthOfUsageAfter(user, Instant.now().atZone(ZoneId.of("UTC")).minus(1, ChronoUnit.MONTHS).toInstant());
        final YearMonth bookingMonth = YearMonth.from(schedule.getMonthOfUsage().atZone(ZoneId.of("UTC")));
        final long bookingsThatMonth = userBookings.stream()
                .filter(userBooking -> YearMonth.from(userBooking.getMonthOfUsage().atZone(ZoneId.of("UTC"))).equals(bookingMonth)).count();
        if (bookingsThatMonth >= user.getFlowers()) {
            throw new IllegalArgumentException("You have already booked 3 items this month.");
        }
        scheduleRepository.save(schedule);
    }

    public void getItemBack(Long userId) {
        final User user = userRepository.findById(userId).orElseThrow();
        user.setFlowers(user.getFlowers() - 1);
        if(user.getFlowers() < 0) {
            return;
        }
        userRepository.save(user);
    }
}
