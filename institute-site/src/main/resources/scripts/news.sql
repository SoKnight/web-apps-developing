TRUNCATE TABLE news;
ALTER SEQUENCE news_id_seq RESTART WITH 1;

INSERT INTO news (title, content, cover_url, published_at) VALUES
    (
        'Успешный старт курса «Искусственный интеллект и машинное обучение»',
        'Наши студенты начинают обучение на новом курсе, где узнают, как создавать модели машинного обучения и решать реальные задачи. Приглашаем всех заинтересованных на проведение хакатона в конце семестра! 🚀',
        '/images/news/ai.jpg',
        current_date
    ),
    (
        'Семинар «Кибербезопасность: вызовы современности»',
        'Сегодня состоялся семинар с участием экспертов из ведущих IT-компаний. Участники обсудили новые методы защиты от кибератак. Каждый гость получил доступ к эксклюзивным онлайн-материалам. 💻',
        '/images/news/cyber-security.jpg',
        '2025-02-19'
    ),
    (
        'Набор на стажировки в топовые IT-компании',
        'Для студентов открыт конкурс на стажировки в топовые IT-компании — от производства программного обеспечения до аналитики данных! Успей подать заявку и стать частью технологического прорыва. 💼',
        null,
        '2024-12-27'
    );
