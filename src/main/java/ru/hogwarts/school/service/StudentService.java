package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private static long numbOfStudents = 0;

    public Student createStudent(Student student) {
        if (students.containsKey(student.getId())) {
            throw new StudentAlreadyExistsException("A student with the same id already exists!");
        }
        else if (student.getAge() <= 0) {
            throw new BadAgeException("Incorrect student age!");
        }
        else if (student.getId() <= 0) {
            throw new BadIdException("Incorrect student ID!");
        }
        students.put(student.getId(), student);
        numbOfStudents++;
        return student;
    }

    public Student editStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            throw new StudentNotFoundException("Student is not found!");
        }
        else if (student.getAge() <= 0) {
            throw new BadAgeException("Incorrect student age!");
        }
        students.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id) {
        if (students.containsKey(id)) {
            return students.get(id);
        }
        else {
            throw new StudentNotFoundException("Student is not fount!");
        }
    }

    public Student deleteStudent(long id) {
        if (students.containsKey(id)) {
            numbOfStudents--;
            return students.remove(id);
        }
        else {
            throw new StudentNotFoundException("Student is not found!");
        }
    }

    public List<Student> getStudentsByAge(int age) {
        List<Student> result = new ArrayList<>(List.of());

        students.forEach((id, student) -> {
                if (student.getAge() == age) {
                    result.add(student);
                }
            }
        );

        if (!result.isEmpty()) {
            return result;
        }
        else {
            throw new NoStudentsOfThisAgeException("No students of this age!");
        }
    }
}