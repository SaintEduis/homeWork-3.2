package ru.hogwarts.school.exception;

public class NoFacultiesOfThisColorException extends RuntimeException {
    public NoFacultiesOfThisColorException(String string) {
        System.out.println(string);
    }
}
