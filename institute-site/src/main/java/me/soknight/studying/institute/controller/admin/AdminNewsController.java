package me.soknight.studying.institute.controller.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.soknight.studying.institute.model.ErrorModel;
import me.soknight.studying.institute.model.NewsItem;
import me.soknight.studying.institute.model.NewsItemDto;
import me.soknight.studying.institute.repository.NewsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static me.soknight.studying.institute.controller.PagesController.ISO_DATE_RU;
import static me.soknight.studying.institute.util.FragmentInjector.injectAdminFragment;
import static me.soknight.studying.institute.util.FragmentInjector.injectErrorFragment;

@Controller
@AllArgsConstructor
public final class AdminNewsController {

    private final NewsRepository newsRepository;

    @GetMapping("/admin/news")
    public String renderNewsPage(Model model) {
        List<NewsItem> news = newsRepository.findAll(NewsRepository.DEFAULT_SORT);
        model.addAttribute("news", news);
        injectAdminFragment(model, "news", "ИКНТ - Новости");
        return "admin_page";
    }

    @GetMapping("/admin/news/create")
    public String createNewsItemPage(Model model) {
        model.addAttribute("newsItem", new NewsItem());
        model.addAttribute("todayDate", LocalDate.now().format(ISO_DATE_RU));
        model.addAttribute("formTitle", "Добавление новости");
        model.addAttribute("formButton", "Добавить");
        injectAdminFragment(model, "crud/news_item", "ИКНТ - Добавление новости");
        return "admin_page";
    }

    @PostMapping("/admin/news/create")
    public String createNewsItem(@Valid NewsItemDto dto) {
        newsRepository.save(new NewsItem(dto));
        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/{newsItemId}/edit")
    public String editNewsItemPage(@PathVariable long newsItemId, Model model) {
        newsRepository.findById(newsItemId).ifPresentOrElse(
                newsItem -> {
                    model.addAttribute("newsItem", newsItem);
                    model.addAttribute("formTitle", "Редактирование новости");
                    model.addAttribute("formButton", "Сохранить");
                    injectAdminFragment(model, "crud/news_item", "ИКНТ - Редактирование новости");
                },
                () -> injectErrorFragment(model, ErrorModel.ERROR_NEWS_ITEM_NOT_FOUND)
        );

        return "admin_page";
    }

    @PostMapping("/admin/news/{newsItemId}/edit")
    public String editNewsItem(@PathVariable long newsItemId, @Valid NewsItemDto dto, Model model) {
        Optional<NewsItem> newsItem = newsRepository.findById(newsItemId);
        if (newsItem.isEmpty()) {
            injectErrorFragment(model, ErrorModel.ERROR_NEWS_ITEM_NOT_FOUND);
            return "admin_page";
        }

        newsItem.get().update(dto);
        newsRepository.save(newsItem.get());
        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/{newsItemId}/delete")
    public String deleteNewsItem(@PathVariable long newsItemId) {
        newsRepository.deleteById(newsItemId);
        return "redirect:/admin/news";
    }

}
