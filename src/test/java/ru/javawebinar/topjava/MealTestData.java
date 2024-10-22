package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static Meal USER_MEAL_1 = new Meal(START_SEQ + 3, LocalDateTime.of(2024, Month.OCTOBER, 20, 8, 0), "Завтрак", 500);
    public static Meal USER_MEAL_2 = new Meal(START_SEQ + 4, LocalDateTime.of(2024, Month.OCTOBER, 20, 12, 0), "Обед", 600);
    public static Meal USER_MEAL_3 = new Meal(START_SEQ + 5, LocalDateTime.of(2024, Month.OCTOBER, 20, 17, 0), "Полдник", 500);
    public static Meal USER_MEAL_4 = new Meal(START_SEQ + 6, LocalDateTime.of(2024, Month.OCTOBER, 20, 20, 0), "Ужин", 300);
    public static Meal USER_MEAL_5 = new Meal(START_SEQ + 7, LocalDateTime.of(2024, Month.OCTOBER, 21, 8, 0), "Завтрак", 500);
    public static Meal USER_MEAL_6 = new Meal(START_SEQ + 8, LocalDateTime.of(2024, Month.OCTOBER, 21, 12, 0), "Обед", 600);
    public static Meal USER_MEAL_7 = new Meal(START_SEQ + 9, LocalDateTime.of(2024, Month.OCTOBER, 21, 14, 0), "Перекус", 300);
    public static Meal USER_MEAL_8 = new Meal(START_SEQ + 10, LocalDateTime.of(2024, Month.OCTOBER, 21, 17, 0), "Полдник", 500);
    public static Meal USER_MEAL_9 = new Meal(START_SEQ + 11, LocalDateTime.of(2024, Month.OCTOBER, 21, 20, 0), "Ужин", 300);
    public static Meal ADMIN_MEAL_1 = new Meal(START_SEQ + 12, LocalDateTime.of(2024, Month.OCTOBER, 21, 8, 0), "Завтрак admin", 500);
    public static Meal ADMIN_MEAL_2 = new Meal(START_SEQ + 13, LocalDateTime.of(2024, Month.OCTOBER, 21, 12, 0), "Обед admin", 600);
    public static Meal ADMIN_MEAL_3 = new Meal(START_SEQ + 14, LocalDateTime.of(2024, Month.OCTOBER, 21, 17, 0), "Полдник admin", 500);
    public static Meal ADMIN_MEAL_4 = new Meal(START_SEQ + 15, LocalDateTime.of(2024, Month.OCTOBER, 21, 20, 0), "Ужин admin", 300);

    public static List<Meal> getUserMeals() {
        return Arrays.asList(USER_MEAL_9,
                USER_MEAL_8,
                USER_MEAL_7,
                USER_MEAL_6,
                USER_MEAL_5,
                USER_MEAL_4,
                USER_MEAL_3,
                USER_MEAL_2,
                USER_MEAL_1);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
