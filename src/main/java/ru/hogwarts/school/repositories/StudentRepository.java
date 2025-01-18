package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Student findById(long id);

    void deleteById(long id);
}
