package abdumalik.prodev.studentmodule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record StudentRequestDTO(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email String email,
    @NotBlank String password,
    @Pattern(regexp = "\\+?[0-9]{10,15}") String phone,
    LocalDate dateOfBirth,
    String major,
    Integer yearOfStudy
) {}
