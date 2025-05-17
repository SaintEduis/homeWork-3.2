package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController()
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findStudent(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudents(@RequestParam Integer min, @RequestParam Integer max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping({"/faculty/{id}"})
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findFaculty(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.createStudent(student));
        } catch (StudentAlreadyExistsException | BadAgeException | BadIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.editStudent(student));
        } catch (BadAgeException e) {
            return ResponseEntity.badRequest().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 5120) {
            return ResponseEntity.badRequest().body("File is too big!");
        }

        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());

            is.transferTo(os);
        }
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
    }

    //Я не смог понять как выводить сразу несколько изображений в сваггер, поэтому сделал через zip-архив
    @GetMapping(value = "/avatars")
    public ResponseEntity<StreamingResponseBody> downloadAvatars(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Collection<Avatar> avatars = avatarService.getAllAvatars(page, size);

        StreamingResponseBody responseBody = outputStream -> {
            try (ZipOutputStream zipOut = new ZipOutputStream(outputStream)) {
                for (Avatar avatar : avatars) {
                    Path path = Path.of(avatar.getFilePath());
                    String fileName = path.getFileName().toString();
                    try (InputStream inputStream = Files.newInputStream(path)) {
                        ZipEntry zipEntry = new ZipEntry(fileName);
                        zipOut.putNextEntry(zipEntry);
                        inputStream.transferTo(zipOut);
                        zipOut.closeEntry();
                    }
                }
                zipOut.finish();
            }
        };

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"avatars.zip\""
                )
                .body(responseBody);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Integer> getCountOfStudents() {
        try {
            return ResponseEntity.ok(studentService.getCountOfStudents());
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/average")
    public ResponseEntity<Integer> getStudentsAverageAge() {
        return ResponseEntity.ok(studentService.getAverageOfStudentsAge());
    }

    @GetMapping(value = "/five-last")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }
}
