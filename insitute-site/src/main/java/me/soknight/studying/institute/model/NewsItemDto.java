package me.soknight.studying.institute.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;

public record NewsItemDto(
        @NotEmpty String title,
        @NotEmpty String content,
        String coverUrl,
        @NotNull @DateTimeFormat(iso = ISO.DATE) LocalDate publishedAt
) {

    @Override
    public String coverUrl() {
        return coverUrl != null && !coverUrl.isEmpty() ? coverUrl : null;
    }

}
