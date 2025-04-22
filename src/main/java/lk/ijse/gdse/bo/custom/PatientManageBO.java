package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.entity.Patient;

import java.util.List;

public interface PatientManageBO extends SuperBO {

    String generateNextPatientId();

    boolean addPatient(PatientDTO patientDTO);

    List<Patient> searchPatient(String searchText);

    boolean updatePatient(PatientDTO patientDTO);

    boolean deletePatient(String id);

    List<PatientDTO> getAllPatient();
}
