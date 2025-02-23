package me.soknight.studying.institute.repository;

import me.soknight.studying.institute.model.Teacher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Sort DEFAULT_SORT = Sort.by(Sort.Direction.ASC, "lastName", "firstName", "middleName");

}
