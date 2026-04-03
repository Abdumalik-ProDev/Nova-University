package abdumalik.prodev.studentmodule.client;

import abdumalik.prodev.studentmodule.dto.TeacherResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "teacher-module", fallback = TeacherClientFallback.class)
@Tag(name = "Teacher Client", description = "Client for interacting with Teacher Module")
public interface TeacherClient {

    @GetMapping("/api/v1/teachers")
    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers")
    List<TeacherResponseDTO> getAllTeachers();

    @GetMapping("/api/v1/teachers/{id}")
    @Operation(summary = "Get teacher by ID", description = "Retrieves a teacher by their unique ID")
    TeacherResponseDTO getTeacherById(@Parameter(description = "Unique identifier of the teacher") @PathVariable UUID id);
}
