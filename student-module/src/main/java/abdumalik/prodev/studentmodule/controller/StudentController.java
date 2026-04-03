package abdumalik.prodev.studentmodule.controller;

import abdumalik.prodev.studentmodule.dto.StudentRequestDTO;
import abdumalik.prodev.studentmodule.dto.StudentResponseDTO;
import abdumalik.prodev.studentmodule.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create student", description = "Create a new student")
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        StudentResponseDTO response = studentService.createStudent(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STAFF')")
    @Operation(summary = "Get all students", description = "Retrieve all students")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STAFF') or hasRole('STUDENT')")
    @Operation(summary = "Get student by ID", description = "Retrieve a student by ID")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id) {
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update student", description = "Update an existing student")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable UUID id, @Valid @RequestBody StudentRequestDTO dto) {
        StudentResponseDTO response = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete student", description = "Delete a student")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
