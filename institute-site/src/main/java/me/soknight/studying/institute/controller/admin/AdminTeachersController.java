package me.soknight.studying.institute.controller.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.soknight.studying.institute.model.ErrorModel;
import me.soknight.studying.institute.model.Teacher;
import me.soknight.studying.institute.model.TeacherDto;
import me.soknight.studying.institute.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

import static me.soknight.studying.institute.util.FragmentInjector.injectAdminFragment;
import static me.soknight.studying.institute.util.FragmentInjector.injectErrorFragment;

@Controller
@AllArgsConstructor
public final class AdminTeachersController {

    private final TeacherRepository teacherRepository;

    @GetMapping("/admin/teachers")
    public String renderTeachersPage(Model model) {
        List<Teacher> teachers = teacherRepository.findAll(TeacherRepository.DEFAULT_SORT);
        model.addAttribute("teachers", teachers);
        injectAdminFragment(model, "teachers", "ИКНТ - Преподаватели");
        return "admin_page";
    }

    @GetMapping("/admin/teachers/create")
    public String createTeacherPage(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("formTitle", "Добавление преподавателя");
        model.addAttribute("formButton", "Добавить");
        injectAdminFragment(model, "crud/teacher", "ИКНТ - Добавление преподавателя");
        return "admin_page";
    }

    @PostMapping("/admin/teachers/create")
    public String createTeacher(@Valid TeacherDto dto) {
        teacherRepository.save(new Teacher(dto));
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/teachers/{teacherId}/edit")
    public String editTeacherPage(@PathVariable long teacherId, Model model) {
        teacherRepository.findById(teacherId).ifPresentOrElse(
                teacher -> {
                    model.addAttribute("teacher", teacher);
                    model.addAttribute("formTitle", "Редактирование преподавателя");
                    model.addAttribute("formButton", "Сохранить");
                    injectAdminFragment(model, "crud/teacher", "ИКНТ - Редактирование преподавателя");
                },
                () -> injectErrorFragment(model, ErrorModel.ERROR_TEACHER_NOT_FOUND)
        );

        return "admin_page";
    }

    @PostMapping("/admin/teachers/{teacherId}/edit")
    public String editTeacher(@PathVariable long teacherId, @Valid TeacherDto dto, Model model) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) {
            injectErrorFragment(model, ErrorModel.ERROR_TEACHER_NOT_FOUND);
            return "admin_page";
        }

        teacher.get().update(dto);
        teacherRepository.save(teacher.get());
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/teachers/{teacherId}/delete")
    public String deleteTeacher(@PathVariable long teacherId) {
        teacherRepository.deleteById(teacherId);
        return "redirect:/admin/teachers";
    }

}
