package ru.hogwarts.school.exception;

public class FacultyAlreadyExistsException extends RuntimeException{
    public FacultyAlreadyExistsException(String string) {
        System.out.println(string);
    }
}
