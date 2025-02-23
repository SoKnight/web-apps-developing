package me.soknight.studying.institute.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.soknight.studying.institute.model.ErrorModel;
import org.springframework.ui.Model;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FragmentInjector {

    public static void injectAdminFragment(Model model, String page, String pageTitle) {
        model.addAttribute("content", "fragments/admin/%s".formatted(page));
        model.addAttribute("page", page);
        model.addAttribute("pageTitle", pageTitle);
    }

    public static void injectFragment(Model model, String page, String pageTitle) {
        model.addAttribute("content", "fragments/%s".formatted(page));
        model.addAttribute("page", page);
        model.addAttribute("pageTitle", pageTitle);
    }

    public static void injectErrorFragment(Model model, ErrorModel errorModel) {
        injectFragment(model, "error", "ИКНТ - Ошибка");
        errorModel.injectInto(model);
    }

}
