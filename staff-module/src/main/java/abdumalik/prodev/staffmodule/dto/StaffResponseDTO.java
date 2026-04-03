package abdumalik.prodev.staffmodule.dto;

import java.util.UUID;

public record StaffResponseDTO(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    String department,
    String position
) {}
