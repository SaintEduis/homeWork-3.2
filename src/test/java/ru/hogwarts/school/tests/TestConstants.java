package ru.hogwarts.school.tests;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

public class TestConstants {
    public static final Student STUDENT_1 = new Student("Edgar", 19, 1);
    public static final Student STUDENT_2 = new Student("Nikita", 19, 2);
    public static final Student STUDENT_3 = new Student("Andrew", 18, 3);
    public static final Student STUDENT_4 = new Student("Aleksey", 20, 4);
    public static final Student STUDENT_5 = new Student("Maksim", 18, 5);
    public static final Student INVALID_STUDENT_1 = new Student("Null", -1, 4);
    public static final Student INVALID_STUDENT_2 = new Student("Null", 14, -1);
    public static final Student INVALID_STUDENT_3 = new Student("Null", -1, 1);
    public static final Student EDITED_STUDENT_1 = new Student("NoEdgar", 190, 1);
    public static final Student EDITED_STUDENT_2 = new Student("NoNikita", 190, 2);
    public static final Student EDITED_STUDENT_3 = new Student("NoAndrew", 180, 3);
    public static final Student NOT_EXISTS_STUDENT = new Student("NoOne", 1, 999);
    public static final List<Student> LIST_OF_19 = new ArrayList<>(List.of(STUDENT_1, STUDENT_2));
    public static final List<Student> LIST_OF_18 = new ArrayList<>(List.of(STUDENT_3, STUDENT_5));
    public static final List<Student> LIST_OF_20 = new ArrayList<>(List.of(STUDENT_4));

    public static final Faculty FACULTY_1 = new Faculty("First", "Red", 1);
    public static final Faculty FACULTY_2 = new Faculty("Second", "Blue", 2);
    public static final Faculty FACULTY_3 = new Faculty("Third", "Green", 3);
    public static final Faculty FACULTY_4 = new Faculty("Fourth", "Red", 4);
    public static final Faculty FACULTY_5 = new Faculty("Fifth", "Blue", 5);
    public static final Faculty INVALID_FACULTY_1 = new Faculty("Null", "No", -1);
    public static final Faculty EDITED_FACULTY_1 = new Faculty("First_1", "Red_1", 1);
    public static final Faculty EDITED_FACULTY_2 = new Faculty("Second_2", "Blue_2", 2);
    public static final Faculty EDITED_FACULTY_3 = new Faculty("Third_3", "Green_3", 3);
    public static final Faculty NOT_EXISTS_FACULTY = new Faculty("Nothing", "No", 999);
    public static final List<Faculty> LIST_OF_RED = new ArrayList<>(List.of(FACULTY_1, FACULTY_4));
    public static final List<Faculty> LIST_OF_BLUE = new ArrayList<>(List.of(FACULTY_2, FACULTY_5));
    public static final List<Faculty> LIST_OF_GREEN = new ArrayList<>(List.of(FACULTY_3));
}