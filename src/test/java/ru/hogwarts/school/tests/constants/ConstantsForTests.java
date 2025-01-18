package ru.hogwarts.school.tests.constants;

import org.json.JSONObject;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

public class ConstantsForTests {
    public static final Faculty FACULTY_1 = new Faculty(1, "name1", "color1");
    public static final Faculty FACULTY_2 = new Faculty(2, "name2", "color2");
    public static final Faculty FACULTY_3 = new Faculty(3, "name3", "color3");
    public static final Faculty FACULTY_3_PUT = new Faculty(3, "name5", "color5");
    public static final Faculty FACULTY_4 = new Faculty(4, "name4", "color4");

    public static final Student STUDENT_1 = new Student(1, "name1", 16, FACULTY_1);
    public static final Student STUDENT_1_PUT = new Student(1, "name2", 19, FACULTY_1);
    public static final Student STUDENT_2 = new Student(2, "name2",  15, FACULTY_2);
    public static final Student STUDENT_3 = new Student(3, "name3",  13, FACULTY_3);
    public static final Student STUDENT_4 = new Student(1, "name4",  14, FACULTY_4);
}
