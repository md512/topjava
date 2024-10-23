package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND_MEAL = 20;
    public static final Meal userMeal1 = new Meal(START_SEQ + 3, LocalDateTime.of(2024, Month.OCTOBER, 20, 8, 0), "Завтрак", 500);
    public static final Meal userMeal2 = new Meal(START_SEQ + 4, LocalDateTime.of(2024, Month.OCTOBER, 20, 12, 0), "Обед", 600);
    public static final Meal userMeal3 = new Meal(START_SEQ + 5, LocalDateTime.of(2024, Month.OCTOBER, 20, 17, 0), "Полдник", 500);
    public static final Meal userMeal4 = new Meal(START_SEQ + 6, LocalDateTime.of(2024, Month.OCTOBER, 20, 20, 0), "Ужин", 300);
    public static final Meal userMeal5 = new Meal(START_SEQ + 7, LocalDateTime.of(2024, Month.OCTOBER, 21, 8, 0), "Завтрак", 500);
    public static final Meal userMeal6 = new Meal(START_SEQ + 8, LocalDateTime.of(2024, Month.OCTOBER, 21, 12, 0), "Обед", 600);
    public static final Meal userMeal7 = new Meal(START_SEQ + 9, LocalDateTime.of(2024, Month.OCTOBER, 21, 14, 0), "Перекус", 300);
    public static final Meal userMeal8 = new Meal(START_SEQ + 10, LocalDateTime.of(2024, Month.OCTOBER, 21, 17, 0), "Полдник", 500);
    public static final Meal userMeal9 = new Meal(START_SEQ + 11, LocalDateTime.of(2024, Month.OCTOBER, 21, 20, 0), "Ужин", 300);
    public static final Meal adminMeal1 = new Meal(START_SEQ + 12, LocalDateTime.of(2024, Month.OCTOBER, 21, 8, 0), "Завтрак admin", 500);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 13, LocalDateTime.of(2024, Month.OCTOBER, 21, 12, 0), "Обед admin", 600);
    public static final Meal adminMeal3 = new Meal(START_SEQ + 14, LocalDateTime.of(2024, Month.OCTOBER, 21, 17, 0), "Полдник admin", 500);
    public static final Meal adminMeal4 = new Meal(START_SEQ + 15, LocalDateTime.of(2024, Month.OCTOBER, 21, 20, 0), "Ужин admin", 300);
    public static final List<Meal> userMeals = Arrays.asList(userMeal9, userMeal8, userMeal7, userMeal6, userMeal5,
            userMeal4, userMeal3, userMeal2, userMeal1);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDateTime(updated.getDateTime().plusDays(5).plusMinutes(5));
        updated.setDescription("Измененная еда");
        updated.setCalories(1000);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
