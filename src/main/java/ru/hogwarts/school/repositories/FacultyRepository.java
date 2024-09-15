package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import javax.swing.text.Position;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Position> findByAge(int age);
}
