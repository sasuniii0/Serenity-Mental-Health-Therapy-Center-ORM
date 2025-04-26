package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.dto.tm.TherapyProgramTM;

import java.util.ArrayList;
import java.util.List;

public interface TherapyProgramManageBO extends SuperBO {
    List<TherapyProgramDTO> getAllPrograms();

    String generateNextTherapyProId();

    boolean addTherapyProgram(TherapyProgramDTO therapyProgram);

    boolean deleteTherapyProgram(String id);

    boolean updateTherapyProgram(TherapyProgramDTO therapyProgram);

    List<String> loadTherapyProgramsForPatient(String patientId);

    String getProgramNameById(String programId);

    String getProgramIdByName(String selectedProgramName);

    ArrayList<String> getAllProgramsNames();

    String getNextProgramId();

    List<String> getRegisteredProgramsByPatientId(String patientId);

    double getProgramFeeById(String programId);
}
