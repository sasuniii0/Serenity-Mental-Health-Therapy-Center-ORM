package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.TherapyProgram;

import java.util.List;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram> {
    String getLastTherapyProId();

    List<String> loadTherapyProgramsForPatient(String patientId);
}
