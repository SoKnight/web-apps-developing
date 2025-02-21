TRUNCATE TABLE orders;
ALTER SEQUENCE orders_id_seq RESTART WITH 1;

INSERT INTO orders (product_id, quantity, name, email, phone_number, country, post_index, city, city_address, comment) VALUES
    (1, 2, 'Константин', 'konstantin@mail.ru', '78009004545', 'russia', '654123', 'Пермь', 'ул. Ленина, 66', ''),
    (4, 3, 'Владимир', 'vova222@mail.ru', '79874567878', 'russia', '645645', 'Пермь', 'ул. Попова, 30', 'Разверните VPN, пожалуйста :)'),
    (8, 1, 'Александра', 'alex@gmail.com', '79519002828', 'russia', '675125', 'Губаха', 'ул. Пушкина, 12', 'Вы лучший хостинг! Самые низкие цены среди всех аналогов, я счастлива!'),
    (3, 1, 'Николай', 'kolyan@yandex.ru', '79001234567', 'russia', '675828', 'Кунгур', 'ул. Ленина, 52', ''),
    (1, 2, 'Валера', 'valeraaa@yandex.ru', '78292875656', 'russia', '624354', 'Пермь', 'ул. Петропавловская, 92', 'Ох ща как сервер в майнкрафте разверну :D');
