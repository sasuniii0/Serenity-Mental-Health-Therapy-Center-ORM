package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PatientManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.entity.Patient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientManageBOImpl implements PatientManageBO {


    @Override
    public String generateNextPatientId() {
        return "";
    }

    @Override
    public boolean addPatient(PatientDTO patientDTO) {
        return false;
    }

    @Override
    public List<Patient> searchPatient(String searchText) {
        return List.of();
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        return false;
    }

    @Override
    public boolean deletePatient(String id) {
        return false;
    }

    @Override
    public List<PatientDTO> getAllPatient() {
        return List.of();
    }

    @Override
    public String generatePatientId() {
        return "";
    }

    @Override
    public List<String> getPatientIdAndNames() {
        return List.of();
    }
}
