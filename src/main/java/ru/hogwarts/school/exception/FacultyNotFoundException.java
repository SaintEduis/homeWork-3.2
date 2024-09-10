package ru.hogwarts.school.exception;

public class FacultyNotFoundException extends RuntimeException{
    public FacultyNotFoundException(String string) {
        System.out.println(string);
    }
}
