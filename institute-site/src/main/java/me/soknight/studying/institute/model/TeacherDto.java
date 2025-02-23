package me.soknight.studying.institute.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;

public record TeacherDto(
        @NotEmpty String firstName,
        String middleName,
        @NotEmpty String lastName,
        @NotNull @DateTimeFormat(iso = ISO.DATE) LocalDate birthDate,
        @NotEmpty String department,
        String photoUrl
) {

    @Override
    public String middleName() {
        return middleName != null && !middleName.isEmpty() ? middleName : null;
    }

    @Override
    public String photoUrl() {
        return photoUrl != null && !photoUrl.isEmpty() ? photoUrl : null;
    }

}
