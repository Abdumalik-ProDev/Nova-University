package abdumalik.prodev.teachermodule.controller;

import abdumalik.prodev.teachermodule.dto.TeacherRequestDTO;
import abdumalik.prodev.teachermodule.dto.TeacherResponseDTO;
import abdumalik.prodev.teachermodule.service.TeacherService;
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
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
@Tag(name = "Teacher Management", description = "APIs for managing teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create teacher", description = "Create a new teacher")
    public ResponseEntity<TeacherResponseDTO> createTeacher(@Valid @RequestBody TeacherRequestDTO dto) {
        TeacherResponseDTO response = teacherService.createTeacher(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @Operation(summary = "Get all teachers", description = "Retrieve all teachers")
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        List<TeacherResponseDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('TEACHER')")
    @Operation(summary = "Get teacher by ID", description = "Retrieve a teacher by ID")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable UUID id) {
        TeacherResponseDTO teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update teacher", description = "Update an existing teacher")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable UUID id, @Valid @RequestBody TeacherRequestDTO dto) {
        TeacherResponseDTO response = teacherService.updateTeacher(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete teacher", description = "Delete a teacher")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
