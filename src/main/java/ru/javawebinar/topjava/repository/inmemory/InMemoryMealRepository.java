package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            log.info("save new meal id {} for userId {}", meal.getId(), userId);
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        } else if (meal.getUserId() != userId) {
            log.info("meal id {} not found for userId {}", meal.getId(), userId);
            return null;
        }
        // handle case: update, but not present in storage
        log.info("update meal id {} for userId {}", meal.getId(), userId);
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = get(id, userId);
        if (meal == null) {
            return false;
        } else if (meal.getUserId() != userId) {
            log.info("delete: meal id {} not belong to userId {}", meal.getId(), userId);
            return false;
        }
        log.info("delete meal id {} for userId {}", meal.getId(), userId);
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal == null) {
            log.info("get: meal id {} not found", id);
            return null;
        } else if (meal.getUserId() != userId) {
            log.info("get: meal id {} not belong to userId {}", meal.getId(), userId);
            return null;
        }
        return meal;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values()
                .stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

