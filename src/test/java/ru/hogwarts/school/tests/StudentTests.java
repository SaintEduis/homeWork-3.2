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
import ru.hogwarts.school.model.Student;

import static ru.hogwarts.school.tests.constants.ConstantsForTests.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTests {
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
    public void studentPostTest() throws Exception {
        facultyController.createFaculty(FACULTY_1);

        //Student POST test
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", STUDENT_1, Student.class))
                .isNotNull();

        studentController.deleteStudent(1);
        facultyController.deleteFaculty(1);
    }

    @Test
    public void studentGetTests() throws Exception {
        //Faculty POST for student GET test
        facultyController.createFaculty(FACULTY_2);

        //Student POST for student GET tests
        studentController.createStudent(STUDENT_2);

        //Student GET tests
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/2", Student.class))
                .isNotNull();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/?min=10&max=20", Student.class))
                .isNotNull();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/faculty/2", Faculty.class))
                .isNotNull();

        studentController.deleteStudent(2);
        facultyController.deleteFaculty(2);
    }

    @Test
    public void studentPutTest() throws Exception {
        //Faculty POST for student GET test
        facultyController.createFaculty(FACULTY_1);

        //Student POST for student PUT tests
        studentController.createStudent(STUDENT_1);

        //Student PUT test
        this.restTemplate.put("http://localhost:" + port + "/students", STUDENT_1_PUT);

        //Student GET test for PUT test
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1", Student.class))
                .isEqualTo(STUDENT_1_PUT);

        studentController.deleteStudent(1);
        facultyController.deleteFaculty(1);
    }

    @Test
    public void studentDeleteTest() throws Exception {
        //Faculty POST for student DELETE test
        facultyController.createFaculty(FACULTY_3);

        //Student POST test for student DELETE test
        studentController.createStudent(STUDENT_3);

        this.restTemplate.delete("http://localhost:" + port + "/students/3");

        //Student DELETE test
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/3", Student.class))
                .isNull();

        studentController.deleteStudent(3);
        facultyController.deleteFaculty(3);
    }
}
