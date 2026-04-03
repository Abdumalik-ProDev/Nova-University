package abdumalik.prodev.staffmodule.client;

import abdumalik.prodev.staffmodule.dto.StudentResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class StudentClientFallback implements StudentClient {

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        log.error("Failed to retrieve all students from student-module");
        return Collections.emptyList();
    }

    @Override
    public StudentResponseDTO getStudentById(UUID id) {
        log.error("Failed to retrieve student with id {} from student-module", id);
        return null;
    }
}
