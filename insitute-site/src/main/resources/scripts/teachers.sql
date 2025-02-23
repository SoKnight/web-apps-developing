TRUNCATE TABLE teachers;
ALTER SEQUENCE teachers_id_seq RESTART WITH 1;

INSERT INTO teachers (first_name, middle_name, last_name, birth_date, department, photo_url) VALUES
        ('Алексей', 'Сергеевич', 'Иванов', '1975-08-12', 'Информационные технологии', '/images/teachers/man1.png'),
        ('Мария', 'Александровна', 'Петрова', '1980-05-23', 'Прикладная математика', '/images/teachers/woman1.png'),
        ('Евгений', null, 'Сидоров', '1990-03-11', 'Кибербезопасность', '/images/teachers/man2.png'),
        ('Наталья', 'Игоревна', 'Кузнецова', '1988-11-07', 'Искусственный интеллект', null),
        ('Владимир', 'Павлович', 'Смирнов', '1972-02-16', 'Анализ данных', '/images/teachers/man3.png'),
        ('Ольга', 'Викторовна', 'Соколова', '1985-04-15', 'Математическое моделирование', '/images/teachers/woman2.png'),
        ('Дмитрий', null, 'Кузьмин', '1992-09-27', 'Информационные системы', '/images/teachers/man4.png'),
        ('Константин', null, 'Пермяков', '2004-11-30', 'Платформа Java', 'https://avatars.githubusercontent.com/u/46436573');