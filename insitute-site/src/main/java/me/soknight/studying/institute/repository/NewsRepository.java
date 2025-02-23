package me.soknight.studying.institute.repository;

import me.soknight.studying.institute.model.NewsItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsItem, Long> {

    Sort DEFAULT_SORT = Sort.by(Sort.Direction.DESC, "publishedAt");

}
