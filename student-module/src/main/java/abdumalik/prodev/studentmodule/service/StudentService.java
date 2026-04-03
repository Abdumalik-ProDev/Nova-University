package abdumalik.prodev.studentmodule.service;

import abdumalik.prodev.studentmodule.dto.StudentRequestDTO;
import abdumalik.prodev.studentmodule.dto.StudentResponseDTO;
import abdumalik.prodev.studentmodule.exception.StudentNotFoundException;
import abdumalik.prodev.studentmodule.mapper.StudentMapper;
import abdumalik.prodev.studentmodule.model.Student;
import abdumalik.prodev.studentmodule.repository.StudentRepo;
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
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = studentMapper.toEntity(dto);
        student.setPassword(passwordEncoder.encode(dto.password()));
        Student savedStudent = studentRepo.save(student);
        log.info("Student created: {}", savedStudent.getEmail());
        return studentMapper.toDTO(savedStudent);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepo.findAll().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(UUID id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return studentMapper.toDTO(student);
    }

    @Transactional
    public StudentResponseDTO updateStudent(UUID id, StudentRequestDTO dto) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setPhone(dto.phone());
        student.setDateOfBirth(dto.dateOfBirth());
        student.setMajor(dto.major());
        student.setYearOfStudy(dto.yearOfStudy());
        if (dto.password() != null && !dto.password().isEmpty()) {
            student.setPassword(passwordEncoder.encode(dto.password()));
        }
        Student updatedStudent = studentRepo.save(student);
        log.info("Student updated: {}", updatedStudent.getEmail());
        return studentMapper.toDTO(updatedStudent);
    }

    @Transactional
    public void deleteStudent(UUID id) {
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        studentRepo.deleteById(id);
        log.info("Student deleted with id: {}", id);
    }
}
