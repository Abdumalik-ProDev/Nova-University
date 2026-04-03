package abdumalik.prodev.staffmodule.client;

import abdumalik.prodev.staffmodule.dto.TeacherResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "teacher-module", fallback = TeacherClientFallback.class)
@RequestMapping("/api/v1/teachers")
@Tag(name = "Teacher Client", description = "Client for interacting with Teacher Module")
public interface TeacherClient {

    @GetMapping
    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers")
    List<TeacherResponseDTO> getAllTeachers();

    @GetMapping("/{id}")
    @Operation(summary = "Get teacher by ID", description = "Retrieves a teacher by their unique ID")
    TeacherResponseDTO getTeacherById(@Parameter(description = "Unique identifier of the teacher") @PathVariable UUID id);
}
