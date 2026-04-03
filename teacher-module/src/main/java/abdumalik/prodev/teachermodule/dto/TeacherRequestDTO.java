package abdumalik.prodev.teachermodule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TeacherRequestDTO(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email String email,
    @NotBlank String password,
    @Pattern(regexp = "\\+?[0-9]{10,15}") String phone,
    String department,
    String subject
) {}
