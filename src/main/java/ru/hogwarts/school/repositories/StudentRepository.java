package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import javax.swing.text.Position;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Position> findByColor(String color);
}