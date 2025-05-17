package ru.hogwarts.school.exception;

public class BadAgeException extends RuntimeException{
    public BadAgeException(String string) {
        System.out.println(string);
    }
}
