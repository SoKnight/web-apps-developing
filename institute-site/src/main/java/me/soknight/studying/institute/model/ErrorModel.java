package me.soknight.studying.institute.model;

import org.springframework.ui.Model;

@SuppressWarnings("unused")
public record ErrorModel(
        String title,
        String message
) {

    public ErrorModel(String message) {
        this("Ошибка!", message);
    }

    public static final ErrorModel ERROR_NOT_FOUND = new ErrorModel("Запрашиваемая страница не найдена \uD83D\uDE15");
    public static final ErrorModel ERROR_BAD_REQUEST = new ErrorModel("Запрос отправлен неверно \uD83D\uDE15");
    public static final ErrorModel ERROR_NEWS_ITEM_NOT_FOUND = new ErrorModel("Указанная новость не найдена \uD83D\uDE15");
    public static final ErrorModel ERROR_TEACHER_NOT_FOUND = new ErrorModel("Указанный преподаватель не найден \uD83D\uDE15");

    public void injectInto(Model model) {
        model.addAttribute("error", this);
    }

}
