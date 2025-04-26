package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Patient;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient> {
    String getLastPatientId();

    List<Patient> searchPatient(String searchText);

    List<String> getPatientIdAndNames();

    Patient search(String patientId);
}
