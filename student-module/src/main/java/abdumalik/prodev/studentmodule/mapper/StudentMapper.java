package abdumalik.prodev.studentmodule.mapper;

import abdumalik.prodev.studentmodule.dto.StudentRequestDTO;
import abdumalik.prodev.studentmodule.dto.StudentResponseDTO;
import abdumalik.prodev.studentmodule.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setPassword(dto.password());
        student.setPhone(dto.phone());
        student.setDateOfBirth(dto.dateOfBirth());
        student.setMajor(dto.major());
        student.setYearOfStudy(dto.yearOfStudy());
        return student;
    }

    public StudentResponseDTO toDTO(Student student) {
        return new StudentResponseDTO(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getPhone(),
            student.getDateOfBirth(),
            student.getMajor(),
            student.getYearOfStudy()
        );
    }
}
