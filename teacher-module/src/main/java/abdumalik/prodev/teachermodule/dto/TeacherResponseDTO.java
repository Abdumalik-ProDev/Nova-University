package abdumalik.prodev.teachermodule.dto;

import java.util.UUID;

public record TeacherResponseDTO(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    String department,
    String subject
) {}
