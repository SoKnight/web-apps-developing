package me.soknight.studying.institute.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@NoArgsConstructor
@Entity @Table(name = "news")
public class NewsItem {

    public static final DateTimeFormatter USER_FRIENDLY_FORMAT_NO_YEAR = DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru-RU"));
    public static final DateTimeFormatter USER_FRIENDLY_FORMAT_WITH_YEAR = DateTimeFormatter.ofPattern("d MMMM yyyy 'г'", Locale.forLanguageTag("ru-RU"));

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title", nullable = false, columnDefinition = "text")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "cover_url", columnDefinition = "text")
    private String coverUrl;

    @Column(name = "published_at", nullable = false, columnDefinition = "date")
    private LocalDate publishedAt;

    public NewsItem(NewsItemDto dto) {
        this(dto.title(), dto.content(), dto.coverUrl(), dto.publishedAt());
    }

    public NewsItem(String title, String content, String coverUrl, LocalDate publishedAt) {
        this.title = title;
        this.content = content;
        this.coverUrl = coverUrl;
        this.publishedAt = publishedAt;
    }

    public String getUserFriendlyDate() {
        if (publishedAt == null)
            return "Сегодня";

        return switch (Period.between(publishedAt, LocalDate.now()).getDays()) {
            case 2 -> "Позавчера";
            case 1 -> "Вчера";
            case 0 -> "Сегодня";
            default -> {
                var formatter = publishedAt.getYear() < LocalDate.now().getYear()
                        ? USER_FRIENDLY_FORMAT_WITH_YEAR
                        : USER_FRIENDLY_FORMAT_NO_YEAR;

                yield publishedAt.format(formatter);
            }
        };
    }

    public boolean hasCover() {
        return coverUrl != null && !coverUrl.isEmpty();
    }

    public void update(NewsItemDto dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.coverUrl = dto.coverUrl();
        this.publishedAt = dto.publishedAt();
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }

}
