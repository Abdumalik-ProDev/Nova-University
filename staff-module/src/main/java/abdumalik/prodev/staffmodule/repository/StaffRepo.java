package abdumalik.prodev.staffmodule.repository;

import abdumalik.prodev.staffmodule.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StaffRepo extends JpaRepository<Staff, UUID> {
    Optional<Staff> findByEmail(String email);
}
