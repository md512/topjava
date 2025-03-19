package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealStorage implements MealStorage {

    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public InMemoryMealStorage() {
        Arrays.asList(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                        new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                        new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                        new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                        new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                        new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                        new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410))
                .forEach(this::create);
    }

    @Override
    public Meal create(Meal meal) {
        meal.setId(counter.incrementAndGet());
        storage.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        return storage.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }

}
