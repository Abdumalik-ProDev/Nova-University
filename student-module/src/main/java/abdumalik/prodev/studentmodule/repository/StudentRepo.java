package abdumalik.prodev.studentmodule.repository;

import abdumalik.prodev.studentmodule.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepo extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);
}
