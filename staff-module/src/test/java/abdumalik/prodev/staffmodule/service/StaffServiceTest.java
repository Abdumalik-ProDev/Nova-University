package abdumalik.prodev.staffmodule.service;

import abdumalik.prodev.staffmodule.dto.StaffRequestDTO;
import abdumalik.prodev.staffmodule.dto.StaffResponseDTO;
import abdumalik.prodev.staffmodule.exception.StaffNotFoundException;
import abdumalik.prodev.staffmodule.mapper.StaffMapper;
import abdumalik.prodev.staffmodule.model.Staff;
import abdumalik.prodev.staffmodule.repository.StaffRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StaffServiceTest {

    @Mock
    private StaffRepo staffRepo;

    @Mock
    private StaffMapper staffMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StaffService staffService;

    private Staff staff;
    private StaffRequestDTO requestDTO;
    private StaffResponseDTO responseDTO;
    private UUID staffId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        staffId = UUID.randomUUID();
        staff = new Staff();
        staff.setId(staffId);
        staff.setFirstName("John");
        staff.setLastName("Doe");
        staff.setEmail("john.doe@example.com");

        requestDTO = new StaffRequestDTO("John", "Doe", "john.doe@example.com", "password", "1234567890", "IT", "Manager");
        responseDTO = new StaffResponseDTO(staffId, "John", "Doe", "john.doe@example.com", "1234567890", "IT", "Manager");
    }

    @Test
    void createStaff_shouldReturnStaffResponseDTO() {
        when(staffMapper.toEntity(requestDTO)).thenReturn(staff);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(staffRepo.save(staff)).thenReturn(staff);
        when(staffMapper.toDTO(staff)).thenReturn(responseDTO);

        StaffResponseDTO result = staffService.createStaff(requestDTO);

        assertNotNull(result);
        assertEquals("John", result.firstName());
        verify(staffRepo).save(staff);
    }

    @Test
    void getStaffById_shouldReturnStaffResponseDTO() {
        when(staffRepo.findById(staffId)).thenReturn(Optional.of(staff));
        when(staffMapper.toDTO(staff)).thenReturn(responseDTO);

        StaffResponseDTO result = staffService.getStaffById(staffId);

        assertNotNull(result);
        assertEquals(staffId, result.id());
    }

    @Test
    void getStaffById_shouldThrowExceptionWhenNotFound() {
        when(staffRepo.findById(staffId)).thenReturn(Optional.empty());

        assertThrows(StaffNotFoundException.class, () -> staffService.getStaffById(staffId));
    }
}
