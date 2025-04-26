package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatientDAO extends CrudDAO<Patient> {
    String getLastPatientId();

    List<Patient> searchPatient(String searchText);

    List<String> getPatientIdAndNames();

    Patient search(String patientId);

    String getNextId();

    ArrayList<String> getAllPatientNames();

    String getPatientNameById(String patientId);

    String getPatientIdByName(String selectedPatientName);

    Patient getPatientId(String patientId);

    Optional<Patient> findByPK(String patientId);
}
