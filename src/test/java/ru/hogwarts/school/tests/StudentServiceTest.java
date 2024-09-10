package ru.hogwarts.school.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.stream.Stream;

import static ru.hogwarts.school.tests.TestConstants.*;

public class StudentServiceTest {
    private final StudentService out = new StudentService();

    public static Stream<Arguments> provideParamsForCreateStudent() {
        return Stream.of(
                Arguments.of(STUDENT_1),
                Arguments.of(STUDENT_2),
                Arguments.of(STUDENT_3)
        );
    }

    public static Stream<Arguments> provideParamsForEditStudent() {
        return Stream.of(
                Arguments.of(STUDENT_1, EDITED_STUDENT_1, EDITED_STUDENT_1.getAge()),
                Arguments.of(STUDENT_2, EDITED_STUDENT_2, EDITED_STUDENT_2.getAge()),
                Arguments.of(STUDENT_3, EDITED_STUDENT_3, EDITED_STUDENT_3.getAge())
        );
    }

    public static Stream<Arguments> provideParamsForFindStudent() {
        return Stream.of(
                Arguments.of(EDITED_STUDENT_1, EDITED_STUDENT_1.getId()),
                Arguments.of(EDITED_STUDENT_2, EDITED_STUDENT_2.getId()),
                Arguments.of(EDITED_STUDENT_3, EDITED_STUDENT_3.getId())
        );
    }

    public static Stream<Arguments> provideParamsForDeleteStudent() {
        return Stream.of(
                Arguments.of(STUDENT_1),
                Arguments.of(STUDENT_2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForCreateStudent")
    public void createStudent(Student student) {
        out.createStudent(student);
    }

    @Test
    public void invalidCreateStudent() {
        out.createStudent(STUDENT_1);
        out.createStudent(STUDENT_2);
        out.createStudent(STUDENT_3);

        Assertions.assertThrows(StudentAlreadyExistsException.class,
                () -> out.createStudent(STUDENT_1));
        Assertions.assertThrows(BadAgeException.class,
                () -> out.createStudent(INVALID_STUDENT_1));
        Assertions.assertThrows(BadIdException.class,
                () -> out.createStudent(INVALID_STUDENT_2));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEditStudent")
    public void editStudent(Student student, Student editedStudent, int age) {
        out.createStudent(student);
        Assertions.assertEquals(out.editStudent(editedStudent).getAge(), age);
    }

    @Test
    public void invalidEditStudent() {
        out.createStudent(EDITED_STUDENT_1);
        out.createStudent(EDITED_STUDENT_2);

        Assertions.assertThrows(StudentNotFoundException.class,
                () -> out.editStudent(NOT_EXISTS_STUDENT));
        Assertions.assertThrows(BadAgeException.class,
                () -> out.editStudent(INVALID_STUDENT_3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForFindStudent")
    public void findStudent(Student student, long id) {
        out.createStudent(student);

        Assertions.assertEquals(student, out.findStudent(id));
    }

    @Test
    public void invalidFindStudent() {
        Assertions.assertThrows(StudentNotFoundException.class,
                () -> out.findStudent(STUDENT_1.getId()));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForDeleteStudent")
    public void deleteStudent(Student student) {
        out.createStudent(student);

        Assertions.assertEquals(student, out.deleteStudent(student.getId()));
    }

    @Test
    public void invalidDeleteStudent() {
        Assertions.assertThrows(StudentNotFoundException.class,
                () -> out.deleteStudent(STUDENT_1.getId()));
    }

    @Test
    public void getStudentsByAge() {
        out.createStudent(STUDENT_1);
        out.createStudent(STUDENT_2);
        out.createStudent(STUDENT_3);
        out.createStudent(STUDENT_4);
        out.createStudent(STUDENT_5);

        Assertions.assertEquals(LIST_OF_18, out.getStudentsByAge(18));
        Assertions.assertEquals(LIST_OF_19, out.getStudentsByAge(19));
        Assertions.assertEquals(LIST_OF_20, out.getStudentsByAge(20));
    }

    @Test
    public void invalidGetStudentsByAge() {
        Assertions.assertThrows(NoStudentsOfThisAgeException.class,
                () -> out.getStudentsByAge(18));
    }
}
