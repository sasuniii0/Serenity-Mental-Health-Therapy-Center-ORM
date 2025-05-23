package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TherapistDAO extends CrudDAO<Therapist> {
    String getLastTherapistId();

    List<TherapyProgram> getAllPrograms();

    List<TherapyProgram> getAllTherapyPrograms();

    String getNextId();

    ArrayList<String> getAllTherapistNames();

    String getTherapistNameById(String therapistId);

    ArrayList<String> getTherapistNameByProgramId(String programId);

    String getTherapistIdByName(String selectedTherapistName);

    Optional<Therapist> findByPK(String therapistId);

/*
    Map<String, Integer> getTherapistSessionCounts();
*/
}
