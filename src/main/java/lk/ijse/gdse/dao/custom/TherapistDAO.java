package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;

import java.util.List;

public interface TherapistDAO extends CrudDAO<Therapist> {
    String getLastTherapistId();

    List<TherapyProgram> getAllPrograms();

    List<TherapyProgram> getAllTherapyPrograms();
}
