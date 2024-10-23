DELETE FROM user_role;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2024-10-20 08:00:00', 'Завтрак', 500),
       (100000, '2024-10-20 12:00:00', 'Обед', 600),
       (100000, '2024-10-20 17:00:00', 'Полдник', 500),
       (100000, '2024-10-20 20:00:00', 'Ужин', 300),
       (100000, '2024-10-21 08:00:00', 'Завтрак', 500),
       (100000, '2024-10-21 12:00:00', 'Обед', 600),
       (100000, '2024-10-21 14:00:00', 'Перекус', 300),
       (100000, '2024-10-21 17:00:00', 'Полдник', 500),
       (100000, '2024-10-21 20:00:00', 'Ужин', 300),
       (100001, '2024-10-21 08:00:00', 'Завтрак admin', 500),
       (100001, '2024-10-21 12:00:00', 'Обед admin', 600),
       (100001, '2024-10-21 17:00:00', 'Полдник admin', 500),
       (100001, '2024-10-21 20:00:00', 'Ужин admin', 300);