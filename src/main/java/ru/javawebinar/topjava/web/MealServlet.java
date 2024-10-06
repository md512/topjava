package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MapMealStorage;
import ru.javawebinar.topjava.dao.MealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private final MealStorage storage = new MapMealStorage();
    private static final int CALORIES_PER_DAY = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("storage", MealsUtil.convertAllMeal(storage.getAll(), CALORIES_PER_DAY));
            request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
    }
}
