package ru.hogwarts.school.model;

import java.util.Objects;

public class Student {
    private final long id;
    private String name;
    private int age;

    public Student(String name, int age, int id) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.id + this.name + this.age;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Student student = (Student)o;
        return Objects.equals(student.getId(), this.getId());
    }
}
