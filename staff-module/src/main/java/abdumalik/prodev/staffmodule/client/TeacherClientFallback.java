package abdumalik.prodev.staffmodule.client;

import abdumalik.prodev.staffmodule.dto.TeacherResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class TeacherClientFallback implements TeacherClient {

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        log.error("Failed to retrieve all teachers from teacher-module");
        return Collections.emptyList();
    }

    @Override
    public TeacherResponseDTO getTeacherById(UUID id) {
        log.error("Failed to retrieve teacher with id {} from teacher-module", id);
        return null;
    }

}
