package abdumalik.prodev.staffmodule.mapper;

import abdumalik.prodev.staffmodule.dto.StaffRequestDTO;
import abdumalik.prodev.staffmodule.dto.StaffResponseDTO;
import abdumalik.prodev.staffmodule.model.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public Staff toEntity(StaffRequestDTO dto) {
        Staff staff = new Staff();
        staff.setFirstName(dto.firstName());
        staff.setLastName(dto.lastName());
        staff.setEmail(dto.email());
        staff.setPassword(dto.password());
        staff.setPhone(dto.phone());
        staff.setDepartment(dto.department());
        staff.setPosition(dto.position());
        return staff;
    }

    public StaffResponseDTO toDTO(Staff staff) {
        return new StaffResponseDTO(
            staff.getId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getEmail(),
            staff.getPhone(),
            staff.getDepartment(),
            staff.getPosition()
        );
    }
}
