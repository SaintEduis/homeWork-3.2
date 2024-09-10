package ru.hogwarts.school.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String string) {
        System.out.println(string);
    }
}
