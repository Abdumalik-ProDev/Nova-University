package abdumalik.prodev.teachermodule.mapper;

import abdumalik.prodev.teachermodule.dto.TeacherRequestDTO;
import abdumalik.prodev.teachermodule.dto.TeacherResponseDTO;
import abdumalik.prodev.teachermodule.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherRequestDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.firstName());
        teacher.setLastName(dto.lastName());
        teacher.setEmail(dto.email());
        teacher.setPassword(dto.password());
        teacher.setPhone(dto.phone());
        teacher.setDepartment(dto.department());
        teacher.setSubject(dto.subject());
        return teacher;
    }

    public TeacherResponseDTO toDTO(Teacher teacher) {
        return new TeacherResponseDTO(
            teacher.getId(),
            teacher.getFirstName(),
            teacher.getLastName(),
            teacher.getEmail(),
            teacher.getPhone(),
            teacher.getDepartment(),
            teacher.getSubject()
        );
    }
}
