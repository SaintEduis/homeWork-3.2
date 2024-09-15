package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        if (student.getAge() <= 0) {
            throw new BadAgeException("Incorrect student age!");
        }
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        if (student.getAge() <= 0) {
            throw new BadAgeException("Incorrect student age!");
        }
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
}