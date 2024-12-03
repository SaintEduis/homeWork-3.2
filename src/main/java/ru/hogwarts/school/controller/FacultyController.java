package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.BadIdException;
import ru.hogwarts.school.exception.FacultyAlreadyExistsException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        try {
            return ResponseEntity.ok(facultyService.findFaculty(id));
        }
        catch (FacultyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Faculty> getFaculty(@RequestParam String namePart, @RequestParam String colorPart) {
        return ResponseEntity.ok(facultyService.findByNameContainsOrColorContainsIgnoreCase(namePart, colorPart));
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Set<Student>>  geStudentsOfFaculty(@PathVariable long id) {
        return  ResponseEntity.ok(facultyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@org.springframework.web.bind.annotation.RequestBody Faculty faculty) {
        try {
            return ResponseEntity.ok(facultyService.createFaculty(faculty));
        }
        catch (FacultyAlreadyExistsException | BadIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@org.springframework.web.bind.annotation.RequestBody Faculty faculty) {
        try {
            return ResponseEntity.ok(facultyService.editFaculty(faculty));
        }
        catch (FacultyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
