package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.entity.Therapist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapistManageBO extends SuperBO {
    List<TherapistDTO> getAllTherapist();

    boolean addTherapist(TherapistDTO therapistDTO);

    String generateNextTherapistId();

    boolean deleteTherapist(String id);

    boolean updateTherapist(TherapistDTO therapistDTO);

    List<TherapyProgramDTO> getAllPrograms();

    List<TherapyProgramDTO> getAllTherapyPrograms();

    TherapyProgramDTO searchTherapyProgram(String program);

    String getNextTherapistId();

    ArrayList<String> getTherapistNamesByProgramId(String programId);

    String getTherapistIdByName(String selectedTherapistName);

    String getTherapistNameById(String therapistId);

    ArrayList<String> getAllTherapistNames();

    Optional<Therapist> findByPK(String therapistId);
}
