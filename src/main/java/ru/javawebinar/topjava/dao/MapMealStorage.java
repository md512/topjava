package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapMealStorage implements MealStorage {

    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public MapMealStorage() {
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        storage.put(getNewId(), new Meal(counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public void save(Meal meal) {
        if (!isExist(meal.getId())) {
            meal.setId(getNewId());
            storage.put(counter.get(), meal);
        }
    }

    @Override
    public void update(Integer id, Meal meal) {
        if (isExist(id)) {
            meal.setId(id);
            storage.put(id, meal);
        }
    }

    @Override
    public void delete(Integer id) {
        storage.remove(id);
    }

    @Override
    public Meal get(Integer id) {
        return storage.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }

    private int getNewId() {
        return counter.incrementAndGet();
    }

    private boolean isExist(Integer id) {
        return get(id) != null;
    }
}
