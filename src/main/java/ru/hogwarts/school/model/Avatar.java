package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "avatar table")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String filePath;
    long fileSize;
    String mediaType;

    @Lob
    byte[] preview;

    @OneToOne
    Student student;

    public Avatar(String filepath, long fileSize, String mediaType, byte[] data, Student student) {
        this.filePath = filepath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.preview = data;
        this.student = student;
    }

    public Avatar() {}

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getPreview() {
        return preview;
    }

    public void setPreview(byte[] preview) {
        this.preview = preview;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return this.id + this.mediaType + this.student + this.filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(mediaType, avatar.mediaType) && Objects.deepEquals(preview, avatar.preview) && Objects.equals(student, avatar.student);
    }
}
