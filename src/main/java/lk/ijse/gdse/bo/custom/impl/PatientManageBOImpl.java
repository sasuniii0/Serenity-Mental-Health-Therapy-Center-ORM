package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PatientManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dao.custom.QueryDAO;
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.entity.Patient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientManageBOImpl implements PatientManageBO {

    PatientDAO patientDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public String generateNextPatientId() {
        return "";
    }

    @Override
    public boolean addPatient(PatientDTO patientDTO) {
        return patientDAO.save(new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getAddress(),
                patientDTO.getEmail(),
                patientDTO.getMobileNumber(),
                patientDTO.getNic(),
                patientDTO.getGender()
        ));
    }

    @Override
    public List<PatientDTO> searchPatient(String searchText) throws Exception {
        try {
            if (searchText == null || searchText.trim().isEmpty()) {
                throw new IllegalArgumentException("Search text cannot be empty");
            }

            List<Patient> patients = (List<Patient>) patientDAO.search(searchText);
            List<PatientDTO> patientDTOs = new ArrayList<>();

            for (Patient patient : patients) {
                patientDTOs.add(convertToDTO(patient));
            }

            return patientDTOs;
        } catch (Exception e) {
            // Log the error
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, "Error searching patients", e);
            throw new Exception("Failed to search patients: " + e.getMessage(), e);
        }
    }

    private PatientDTO convertToDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getAddress(),
                patient.getEmail(),
                patient.getMobileNumber(),
                patient.getNic(),
                patient.getGender()
        );
    }

    @Override
    public String getPatientNameById(String patientId) {
        return patientDAO.getPatientNameById(patientId);
    }

    @Override
    public String getPatientIdByName(String selectedPatientName) {
        return patientDAO.getPatientIdByName(selectedPatientName);
    }

    @Override
    public ArrayList<String> getAllPatientNames() {
        return patientDAO.getAllPatientNames();
    }

    @Override
    public List<Object[]> getAllEnrollments() {
        return queryDAO.getAllEnrollments();
    }

    @Override
    public long getTotalPatientCount() {
        return patientDAO.getTotalPatientCount();
    }

    @Override
    public Map<String, Long> getPatientCountByGender() {
        return Map.of();
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        return patientDAO.update(new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getAddress(),
                patientDTO.getEmail(),
                patientDTO.getMobileNumber(),
                patientDTO.getNic(),
                patientDTO.getGender()
        ));
    }

    @Override
    public boolean deletePatient(String id) {
        return patientDAO.delete(id);
    }

    @Override
    public List<PatientDTO> getAllPatient() {
        List<Patient> patients = patientDAO.getAll();
        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOs.add(new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getAddress(),
                    patient.getEmail(),
                    patient.getMobileNumber(),
                    patient.getNic(),
                    patient.getGender()
            ));
        }
        return patientDTOs;

    }

    @Override
    public String generatePatientId() {
        return "";
    }

    @Override
    public List<String> getPatientIdAndNames() {
        return List.of();
    }

    @Override
    public String getNextPatientId() {
        return patientDAO.getNextId();
    }
}
