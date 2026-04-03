package abdumalik.prodev.securitymodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        STUDENT, TEACHER, STAFF, ADMIN
    }

}
