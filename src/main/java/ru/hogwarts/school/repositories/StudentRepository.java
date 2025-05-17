package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Student findById(long id);

    void deleteById(long id);

    @Query(value = "SELECT COUNT(*) FROM public.\"student table\"", nativeQuery = true)
    Integer getCountOfStudents();

    @Query(value = "SELECT AVG(age) FROM public.\"student table\"", nativeQuery = true)
    Integer getAverageOfStudentsAge();

    @Query(value = "SELECT * FROM public.\"student table\" ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getLastFiveStudents();
}
