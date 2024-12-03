package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController()
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findStudent(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudents(@RequestParam Integer min, @RequestParam Integer max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping({"/faculty/{id}"})
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findFaculty(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.createStudent(student));
        } catch (StudentAlreadyExistsException | BadAgeException | BadIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.editStudent(student));
        } catch (BadAgeException e) {
            return ResponseEntity.badRequest().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
