package ru.hogwarts.school.tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;

import java.util.Set;

import static ru.hogwarts.school.tests.constants.ConstantsForTests.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyTests {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void facultyPostTest() throws Exception {
        //Faculty POST test
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", FACULTY_1, Faculty.class))
                .isNotNull();

        facultyController.deleteFaculty(1);
    }

    @Test
    public void facultyGetTests() throws Exception {
        //Faculty POST for faculty GET tests
        facultyController.createFaculty(FACULTY_4);

        //Student POST for faculty GET tests
        studentController.createStudent(STUDENT_4);

        //Faculty GET tests
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/4", Faculty.class))
                .isNotNull();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/?namePart=4&colorPart=c", Faculty.class))
                .isNotNull();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/students/4", Set.class))
                .isNotNull();

        studentController.deleteStudent(1);
        facultyController.deleteFaculty(4);
    }

    @Test
    public void facultyPutTest() throws Exception {
        //Faculty POST for faculty PUT test
        facultyController.createFaculty(FACULTY_3);

        //Faculty PUT test
        this.restTemplate.put("http://localhost:" + port + "/faculties", FACULTY_3_PUT);

        //Student GET test for PUT test
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/3", Faculty.class))
                .isEqualTo(FACULTY_3_PUT);

        studentController.deleteStudent(3);
        facultyController.deleteFaculty(3);
    }

    @Test
    public void facultyDeleteTest() throws Exception {
        //Faculty POST for faculty DELETE test
        facultyController.createFaculty(FACULTY_2);

        this.restTemplate.delete("http://localhost:" + port + "/faculties/2");

        //Faculty DELETE test
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/2", Faculty.class))
                .isNull();
    }
}
