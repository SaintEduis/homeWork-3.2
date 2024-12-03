package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@EnableJpaRepositories
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByNameContainsOrColorContainsIgnoreCase(String namePart, String colorPart);

    Faculty findById(long id);
}
