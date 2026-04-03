package abdumalik.prodev.studentmodule.dto;

import java.time.LocalDate;
import java.util.UUID;

public record StudentResponseDTO(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate dateOfBirth,
    String major,
    Integer yearOfStudy
) {}
