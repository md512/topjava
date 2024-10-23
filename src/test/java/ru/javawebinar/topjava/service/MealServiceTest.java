package ru.javawebinar.topjava.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest extends TestCase {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(userMeal1.getId(), USER_ID);
        assertMatch(meal, userMeal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_MEAL, USER_ID));
    }

    @Test
    public void getWrongUser() {
        assertThrows(NotFoundException.class, () -> service.get(userMeal1.getId(), ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(userMeal1.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(userMeal1.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_MEAL, USER_ID));
    }

    @Test
    public void deleteWrongUser() {
        assertThrows(NotFoundException.class, () -> service.delete(userMeal1.getId(), ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> list = service.getBetweenInclusive(LocalDate.of(2024, Month.OCTOBER, 20),
                LocalDate.of(2024, Month.OCTOBER, 20), USER_ID);
        assertMatch(list, Arrays.asList(userMeal4, userMeal3, userMeal2, userMeal1));
    }

    @Test
    public void getBetweenAllInclusive() {
        List<Meal> list = service.getBetweenInclusive(null, null, USER_ID);
        assertMatch(list, userMeals);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), userMeals);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), getUpdated());
    }

    @Test
    public void updateWrongUser() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Meal newMeal = getNew();
        newMeal.setId(created.getId());
        assertMatch(service.get(created.getId(), USER_ID), newMeal);
    }

    @Test
    public void duplicateMealCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, userMeal1.getDateTime(), "Завтрак", 500), USER_ID));
    }
}