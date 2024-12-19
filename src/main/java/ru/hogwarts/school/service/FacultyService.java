package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Set;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id);
    }

    public Faculty findByNameContainsOrColorContainsIgnoreCase(String namePart, String colorPart) {
        return facultyRepository.findByNameContainsOrColorContainsIgnoreCase(namePart, colorPart);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Set<Student> findById(long id) {
        return facultyRepository.findById(id).getStudents();
    }
}