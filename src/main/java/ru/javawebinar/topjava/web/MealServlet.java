package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repository.InMemoryMealStorage;
import ru.javawebinar.topjava.repository.MealStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;
    private MealStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new InMemoryMealStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("doGet: call getAll(), forward to meals.jsp");
            request.setAttribute("storage", MealsUtil.filteredByStreams(storage.getAll(),
                    LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        switch (action) {
            case "delete":
                log.debug("doGet: delete id:{}, redirect to meals", id);
                storage.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;
            case "update":
                log.debug("doGet: update id:{}, get meal from storage", id);
                meal = storage.get(Integer.parseInt(id));
                break;
            case "add":
                log.debug("doGet: add, create empty meal");
                meal = new Meal(null, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0);
                break;
            default:
                log.debug("doGet: no valid actions, redirect to meals");
                response.sendRedirect("meals");
                return;
        }
        log.debug("doGet: forward to editMeal.jsp");
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("editMeal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = TimeUtil.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (id.isEmpty()) {
            log.debug("doPost: create id:{}, dateTime:{}, description:{}, calories:{}", id, dateTime, description, calories);
            storage.create(new Meal(null, dateTime, description, calories));
        } else {
            log.debug("doPost: update id:{}, dateTime:{}, description:{}, calories:{}", id, dateTime, description, calories);
            storage.update(new Meal(Integer.parseInt(id), dateTime, description, calories));
        }
        response.sendRedirect("meals");
    }
}