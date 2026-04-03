package abdumalik.prodev.studentmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;

    @Pattern(regexp = "\\+?[0-9]{10,15}")
    private String phone;

    private LocalDate dateOfBirth;

    private String major;

    private Integer yearOfStudy;
}
