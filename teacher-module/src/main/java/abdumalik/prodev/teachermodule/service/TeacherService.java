package abdumalik.prodev.teachermodule.service;

import abdumalik.prodev.teachermodule.dto.TeacherRequestDTO;
import abdumalik.prodev.teachermodule.dto.TeacherResponseDTO;
import abdumalik.prodev.teachermodule.exception.TeacherNotFoundException;
import abdumalik.prodev.teachermodule.mapper.TeacherMapper;
import abdumalik.prodev.teachermodule.model.Teacher;
import abdumalik.prodev.teachermodule.repository.TeacherRepo;
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
public class TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TeacherResponseDTO createTeacher(TeacherRequestDTO dto) {
        Teacher teacher = teacherMapper.toEntity(dto);
        teacher.setPassword(passwordEncoder.encode(dto.password()));
        Teacher savedTeacher = teacherRepo.save(teacher);
        log.info("Teacher created: {}", savedTeacher.getEmail());
        return teacherMapper.toDTO(savedTeacher);
    }

    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepo.findAll().stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TeacherResponseDTO getTeacherById(UUID id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));
        return teacherMapper.toDTO(teacher);
    }

    @Transactional
    public TeacherResponseDTO updateTeacher(UUID id, TeacherRequestDTO dto) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));
        teacher.setFirstName(dto.firstName());
        teacher.setLastName(dto.lastName());
        teacher.setEmail(dto.email());
        teacher.setPhone(dto.phone());
        teacher.setDepartment(dto.department());
        teacher.setSubject(dto.subject());
        if (dto.password() != null && !dto.password().isEmpty()) {
            teacher.setPassword(passwordEncoder.encode(dto.password()));
        }
        Teacher updatedTeacher = teacherRepo.save(teacher);
        log.info("Teacher updated: {}", updatedTeacher.getEmail());
        return teacherMapper.toDTO(updatedTeacher);
    }

    @Transactional
    public void deleteTeacher(UUID id) {
        if (!teacherRepo.existsById(id)) {
            throw new TeacherNotFoundException("Teacher not found with id: " + id);
        }
        teacherRepo.deleteById(id);
        log.info("Teacher deleted with id: {}", id);
    }
}
