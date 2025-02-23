package me.soknight.studying.institute.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity @Table(name = "news")
public class NewsItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title", nullable = false, columnDefinition = "text")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "cover_url", nullable = false, columnDefinition = "text")
    private String coverUrl;

    @Column(name = "published_at", nullable = false, columnDefinition = "timestamp")
    private Instant publishedAt;

    public NewsItem(String title, String content, String coverUrl, Instant publishedAt) {
        this.title = title;
        this.content = content;
        this.coverUrl = coverUrl;
        this.publishedAt = publishedAt;
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
