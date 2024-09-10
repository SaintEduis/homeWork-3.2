package ru.hogwarts.school.exception;

public class NoStudentsOfThisAgeException extends RuntimeException{
    public NoStudentsOfThisAgeException(String string) {
        System.out.println(string);
    }
}
