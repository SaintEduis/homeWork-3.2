package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.BadIdException;
import ru.hogwarts.school.exception.FacultyAlreadyExistsException;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.NoFacultiesOfThisColorException;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private static long numbOfFaculties = 0;

    public Faculty createFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            throw new FacultyAlreadyExistsException("A faculty with the same id already exists!");
        }
        else if (faculty.getId() <= 0) {
            throw new BadIdException("Incorrect faculty ID!");
        }
        faculties.put(faculty.getId(), faculty);
        numbOfFaculties++;
        return faculty;
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            throw new FacultyNotFoundException("Faculty is not found!");
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id ) {
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Faculty is not found!");
        }
        return faculties.get(id);
    }

    public Faculty deleteFaculty(long id) {
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Faculty is not found!");
        }
        numbOfFaculties--;
        return faculties.remove(id);
    }

    public List<Faculty> getFacultiesByColor(String color) {
        List<Faculty> result = new ArrayList<>(List.of());

        faculties.forEach((id, student) -> {
                if (student.getColor().equals(color)) {
                    result.add(student);
                }
        });

        if (!result.isEmpty()) {
            return result;
        }
        else {
            throw new NoFacultiesOfThisColorException("No faculties of this color!");
        }
    }
}