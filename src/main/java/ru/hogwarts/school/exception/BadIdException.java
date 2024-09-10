package ru.hogwarts.school.exception;

public class BadIdException extends RuntimeException{
    public BadIdException(String string) {
        System.out.println(string);
    }
}
