package ru.hogwarts.school.model;

import java.util.Objects;

public class Faculty {
    private final long id;
    private String name;
    private String color;

    public Faculty(String name, String color, long id) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.id + this.name + this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Faculty faculty = (Faculty)o;
        return Objects.equals(this.getId(), faculty.getId());
    }
}
