package abdumalik.prodev.staffmodule.client;

import abdumalik.prodev.staffmodule.dto.StudentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "student-module", fallback = StudentClientFallback.class)
@Tag(name = "Student Client", description = "Client for interacting with Student Module")
public interface StudentClient {

    @GetMapping("/api/v1/students")
    @Operation(summary = "Get all students", description = "Retrieves a list of all students")
    List<StudentResponseDTO> getAllStudents();

    @GetMapping("/api/v1/students/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieves a student by their unique ID")
    StudentResponseDTO getStudentById(@Parameter(description = "Unique identifier of the student") @PathVariable UUID id);
}
