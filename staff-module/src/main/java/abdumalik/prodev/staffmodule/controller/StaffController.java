package abdumalik.prodev.staffmodule.controller;

import abdumalik.prodev.staffmodule.dto.StaffRequestDTO;
import abdumalik.prodev.staffmodule.dto.StaffResponseDTO;
import abdumalik.prodev.staffmodule.service.StaffService;
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
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@Tag(name = "Staff Management", description = "APIs for managing staff")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Create staff", description = "Create a new staff member")
    public ResponseEntity<StaffResponseDTO> createStaff(@Valid @RequestBody StaffRequestDTO dto) {
        StaffResponseDTO response = staffService.createStaff(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasRole('STAFF')")
    @Operation(summary = "Get all staff", description = "Retrieve all staff members")
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        List<StaffResponseDTO> staff = staffService.getAllStaff();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasRole('STAFF')")
    @Operation(summary = "Get staff by ID", description = "Retrieve a staff member by ID")
    public ResponseEntity<StaffResponseDTO> getStaffById(@PathVariable UUID id) {
        StaffResponseDTO staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/by-email")
    @PreAuthorize("hasAuthority('ADMIN') or hasRole('STAFF')")
    @Operation(summary = "Get staff by email", description = "Retrieve a staff member by email")
    public ResponseEntity<StaffResponseDTO> getStaffByEmail(@RequestParam String email) {
        StaffResponseDTO staff = staffService.getStaffByEmail(email);
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update staff", description = "Update an existing staff member")
    public ResponseEntity<StaffResponseDTO> updateStaff(@PathVariable UUID id, @Valid @RequestBody StaffRequestDTO dto) {
        StaffResponseDTO response = staffService.updateStaff(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete staff", description = "Delete a staff member")
    public ResponseEntity<Void> deleteStaff(@PathVariable UUID id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

}
