package me.soknight.studying.institute.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@NoArgsConstructor
@Entity @Table(name = "teachers")
public class Teacher {

    public static final DateTimeFormatter USER_FRIENDLY_FORMAT = DateTimeFormatter.ofPattern("d MMMM yyyy 'г'", Locale.forLanguageTag("ru-RU"));

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", columnDefinition = "date")
    private LocalDate birthDate;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "photo_url", columnDefinition = "text")
    private String photoUrl;

    public Teacher(TeacherDto dto) {
        this(dto.firstName(), dto.middleName(), dto.lastName(), dto.birthDate(), dto.department(), dto.photoUrl());
    }

    public Teacher(String firstName, String middleName, String lastName, LocalDate birthDate, String department, String photoUrl) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.department = department;
        this.photoUrl = photoUrl;
    }

    public String getShortName() {
        return Stream.of(
                lastName,
                (firstName.substring(0, 1) + '.'),
                middleName != null && !middleName.isEmpty() ?( middleName.substring(0, 1) + '.') : null
        ).filter(Objects::nonNull).collect(Collectors.joining(" "));
    }

    public boolean hasBirthDate() {
        return getAge() > 0;
    }

    public String getUserFriendlyBirthDate() {
        return birthDate != null ? USER_FRIENDLY_FORMAT.format(birthDate) : null;
    }

    public String getUserFriendlyAge() {
        int age = getAge();
        String ageStr = String.valueOf(age);
        char lastChar = ageStr.charAt(ageStr.length() - 1);

        return switch (lastChar) {
            case '1' -> "%d год".formatted(age);
            case '2', '3', '4' -> "%d года".formatted(age);
            default -> "%d лет".formatted(age);
        };
    }

    public int getAge() {
        return birthDate != null ? Math.max(Period.between(birthDate, LocalDate.now()).getYears(), 0) : 0;
    }

    public boolean hasPhoto() {
        return photoUrl != null && !photoUrl.isEmpty();
    }

    public void update(TeacherDto dto) {
        this.firstName = dto.firstName();
        this.middleName = dto.middleName();
        this.lastName = dto.lastName();
        this.birthDate = dto.birthDate();
        this.department = dto.department();
        this.photoUrl = dto.photoUrl();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", department='" + department + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

}
