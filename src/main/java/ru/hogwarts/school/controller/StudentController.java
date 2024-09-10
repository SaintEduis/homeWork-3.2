package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController()
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/filter{age}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int age) {
        try {
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        } catch (NoStudentsOfThisAgeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        try {
            return ResponseEntity.ok(studentService.findStudent(id));
        }
        catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.createStudent(student));
        }
        catch (StudentAlreadyExistsException | BadAgeException | BadIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        try {
            return ResponseEntity.ok(studentService.deleteStudent(id));
        }
        catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<Student> editStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.editStudent(student));
        }
        catch (BadAgeException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
