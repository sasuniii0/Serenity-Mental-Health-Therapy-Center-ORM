package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.dto.tm.TherapyProgramTM;

import java.util.List;

public interface TherapyProgramManageBO extends SuperBO {
    List<TherapyProgramDTO> getAllPrograms();

    String generateNextTherapyProId();

    boolean addTherapyProgram(TherapyProgramDTO therapyProgram);

    boolean deleteTherapyProgram(String id);

    boolean updateTherapyProgram(TherapyProgramDTO therapyProgram);
}
