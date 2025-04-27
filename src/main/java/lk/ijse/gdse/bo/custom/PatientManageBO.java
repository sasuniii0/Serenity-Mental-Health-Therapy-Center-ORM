package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PatientManageBO extends SuperBO {

    String generateNextPatientId();

    boolean addPatient(PatientDTO patientDTO);
    
    boolean updatePatient(PatientDTO patientDTO);

    boolean deletePatient(String id);

    List<PatientDTO> getAllPatient();

    String generatePatientId();

    List<String> getPatientIdAndNames();

    String getNextPatientId();

    List<PatientDTO> searchPatient(String searchText) throws Exception;

    String getPatientNameById(String patientId);

    String getPatientIdByName(String selectedPatientName);

    ArrayList<String> getAllPatientNames();

    List<Object[]> getAllEnrollments();

    long getTotalPatientCount();

    Map<String, Long> getPatientCountByGender();
}
