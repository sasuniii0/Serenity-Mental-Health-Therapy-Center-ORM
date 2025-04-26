package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram> {
    String getLastTherapyProId();

    List<String> loadTherapyProgramsForPatient(String patientId);

    TherapyProgram getProgramId(String programId);

    Optional<TherapyProgram> findByPK(String programId);

    String getNextId();

    ArrayList<String> getAllProgramNames();

    String getProgramIdByName(String selectedProgramName);

    String getProgramNameById(String programId);

    List<String> getRegisteredProgramsByPatientId(String patientId);

    double getProgramFeeById(String programId);
}
