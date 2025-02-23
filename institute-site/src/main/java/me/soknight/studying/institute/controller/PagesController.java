package me.soknight.studying.institute.controller;

import lombok.AllArgsConstructor;
import me.soknight.studying.institute.repository.NewsRepository;
import me.soknight.studying.institute.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static me.soknight.studying.institute.util.FragmentInjector.injectFragment;

@Controller
@AllArgsConstructor
public final class PagesController {

    public static final DateTimeFormatter ISO_DATE_RU = DateTimeFormatter.ISO_DATE.localizedBy(Locale.forLanguageTag("ru-RU"));

    private final NewsRepository newsRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping("/")
    public String redirectToNews() {
        return "redirect:/about-us";
    }

    @GetMapping("/about-us")
    public String renderAboutUsPage(Model model) {
        injectFragment(model, "about-us", "ИКНТ - О нас");
        return "page";
    }

    @GetMapping("/news")
    public String renderNewsPage(Model model) {
        model.addAttribute("news", newsRepository.findAll(NewsRepository.DEFAULT_SORT));
        injectFragment(model, "news", "ИКНТ - Новости");
        return "page";
    }

    @GetMapping("/teachers")
    public String renderTeachersPage(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll(TeacherRepository.DEFAULT_SORT));
        injectFragment(model, "teachers", "ИКНТ - Преподаватели");
        return "page";
    }

}
