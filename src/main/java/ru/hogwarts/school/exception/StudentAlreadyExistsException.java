package ru.hogwarts.school.exception;

public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String string) {
        System.out.println(string);
    }
}
