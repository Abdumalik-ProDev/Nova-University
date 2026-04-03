package abdumalik.prodev.staffmodule.service;

import abdumalik.prodev.staffmodule.dto.StaffRequestDTO;
import abdumalik.prodev.staffmodule.dto.StaffResponseDTO;
import abdumalik.prodev.staffmodule.exception.StaffNotFoundException;
import abdumalik.prodev.staffmodule.mapper.StaffMapper;
import abdumalik.prodev.staffmodule.model.Staff;
import abdumalik.prodev.staffmodule.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepo staffRepo;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StaffResponseDTO createStaff(StaffRequestDTO dto) {
        Staff staff = staffMapper.toEntity(dto);
        staff.setPassword(passwordEncoder.encode(dto.password()));
        Staff savedStaff = staffRepo.save(staff);
        log.info("Staff created: {}", savedStaff.getEmail());
        return staffMapper.toDTO(savedStaff);
    }

    public List<StaffResponseDTO> getAllStaff() {
        return staffRepo.findAll().stream()
                .map(staffMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StaffResponseDTO getStaffById(UUID id) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with id: " + id));
        return staffMapper.toDTO(staff);
    }

    public StaffResponseDTO getStaffByEmail(String email) {
        Staff staff = staffRepo.findByEmail(email)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with email: " + email));
        return staffMapper.toDTO(staff);
    }

    @Transactional
    public StaffResponseDTO updateStaff(UUID id, StaffRequestDTO dto) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with id: " + id));
        staff.setFirstName(dto.firstName());
        staff.setLastName(dto.lastName());
        staff.setEmail(dto.email());
        staff.setPhone(dto.phone());
        staff.setDepartment(dto.department());
        staff.setPosition(dto.position());
        if (dto.password() != null && !dto.password().isEmpty()) {
            staff.setPassword(passwordEncoder.encode(dto.password()));
        }
        Staff updatedStaff = staffRepo.save(staff);
        log.info("Staff updated: {}", updatedStaff.getEmail());
        return staffMapper.toDTO(updatedStaff);
    }

    @Transactional
    public void deleteStaff(UUID id) {
        if (!staffRepo.existsById(id)) {
            throw new StaffNotFoundException("Staff not found with id: " + id);
        }
        staffRepo.deleteById(id);
        log.info("Staff deleted with id: {}", id);
    }

}