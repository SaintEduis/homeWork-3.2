package ru.hogwarts.school.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.stream.Stream;

import static ru.hogwarts.school.tests.TestConstants.*;

public class FacultyServiceTest {
    private final FacultyService out = new FacultyService();

    public static Stream<Arguments> provideParamsForCreateFaculty() {
        return Stream.of(
                Arguments.of(FACULTY_1),
                Arguments.of(FACULTY_2),
                Arguments.of(FACULTY_3)
        );
    }

    public static Stream<Arguments> provideParamsForEditFaculty() {
        return Stream.of(
                Arguments.of(FACULTY_1, EDITED_FACULTY_1, EDITED_FACULTY_1.getColor()),
                Arguments.of(FACULTY_2, EDITED_FACULTY_2, EDITED_FACULTY_2.getColor()),
                Arguments.of(FACULTY_3, EDITED_FACULTY_3, EDITED_FACULTY_3.getColor())
        );
    }

    public static Stream<Arguments> provideParamsForFindFaculty() {
        return Stream.of(
                Arguments.of(EDITED_FACULTY_1, EDITED_FACULTY_1.getId()),
                Arguments.of(EDITED_FACULTY_2, EDITED_FACULTY_2.getId()),
                Arguments.of(EDITED_FACULTY_3, EDITED_FACULTY_3.getId())
        );
    }

    public static Stream<Arguments> provideParamsForDeleteFaculty() {
        return Stream.of(
                Arguments.of(FACULTY_1),
                Arguments.of(FACULTY_2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForCreateFaculty")
    public void createFaculty(Faculty faculty) {
        out.createFaculty(faculty);
    }

    @Test
    public void invalidCreateFaculty() {
        out.createFaculty(FACULTY_1);
        out.createFaculty(FACULTY_2);
        out.createFaculty(FACULTY_3);

        Assertions.assertThrows(FacultyAlreadyExistsException.class,
                () -> out.createFaculty(FACULTY_1));
        Assertions.assertThrows(BadIdException.class,
                () -> out.createFaculty(INVALID_FACULTY_1));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEditFaculty")
    public void editFaculty(Faculty faculty, Faculty editedFaculty, String color) {
        out.createFaculty(faculty);
        Assertions.assertEquals(out.editFaculty(editedFaculty).getColor(), color);
    }

    @Test
    public void invalidEditFaculty() {
        out.createFaculty(EDITED_FACULTY_1);
        out.createFaculty(EDITED_FACULTY_2);

        Assertions.assertThrows(FacultyNotFoundException.class,
                () -> out.editFaculty(NOT_EXISTS_FACULTY));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForFindFaculty")
    public void findFaculty(Faculty faculty, long id) {
        out.createFaculty(faculty);

        Assertions.assertEquals(faculty, out.findFaculty(id));
    }

    @Test
    public void invalidFindFaculty() {
        Assertions.assertThrows(FacultyNotFoundException.class,
                () -> out.findFaculty(FACULTY_1.getId()));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForDeleteFaculty")
    public void deleteFaculty(Faculty faculty) {
        out.createFaculty(faculty);

        Assertions.assertEquals(faculty, out.deleteFaculty(faculty.getId()));
    }

    @Test
    public void invalidDeleteFaculty() {
        Assertions.assertThrows(FacultyNotFoundException.class,
                () -> out.deleteFaculty(FACULTY_1.getId()));
    }

    @Test
    public void getFacultiesByAge() {
        out.createFaculty(FACULTY_1);
        out.createFaculty(FACULTY_2);
        out.createFaculty(FACULTY_3);
        out.createFaculty(FACULTY_4);
        out.createFaculty(FACULTY_5);

        Assertions.assertEquals(LIST_OF_RED, out.getFacultiesByColor("Red"));
        Assertions.assertEquals(LIST_OF_BLUE, out.getFacultiesByColor("Blue"));
        Assertions.assertEquals(LIST_OF_GREEN, out.getFacultiesByColor("Green"));
    }

    @Test
    public void invalidGetFacultiesByAge() {
        Assertions.assertThrows(NoFacultiesOfThisColorException.class,
                () -> out.getFacultiesByColor("Blue"));
    }
}
