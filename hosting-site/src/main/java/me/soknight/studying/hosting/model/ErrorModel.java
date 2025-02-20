package me.soknight.studying.hosting.model;

import org.springframework.ui.Model;

public record ErrorModel(
        String title,
        String message
) {

    public static final ErrorModel ERROR_NOT_FOUND = new ErrorModel(
            "Ошибка!",
            "Запрашиваемая страница не найдена \uD83D\uDE15"
    );

    public void injectInto(Model model) {
        model.addAttribute("error", this);
    }

}
