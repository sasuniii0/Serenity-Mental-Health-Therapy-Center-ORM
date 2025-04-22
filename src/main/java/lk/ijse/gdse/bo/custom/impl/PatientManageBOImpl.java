package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PatientManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.entity.Patient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PatientManageBOImpl implements PatientManageBO {

    PatientDAO patientDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public String generateNextPatientId() {
        String lastPatientId = patientDAO.getLastPatientId();
        if (lastPatientId != null) {
            int id = Integer.parseInt(lastPatientId.replace("P", ""));
            id++;
            return "P" + id;
        } else {
            return "P1";
        }
    }

    @Override
    public boolean addPatient(PatientDTO patientDTO) {
        return patientDAO.save(new Patient(patientDTO.getId(), patientDTO.getName(), patientDTO.getNic(), patientDTO.getAddress(), patientDTO.getContactNumber(), patientDTO.getEmail(), patientDTO.getDateOfBirth()));
    }

//    @Override
//    public boolean addPatient(PatientDTO patientDTO) {
//       return patientDAO.addPatient(new Patient(patientDTO));
//    }

    @Override
    public List<Patient> searchPatient(String searchText) {
        return patientDAO.searchPatient(searchText);
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        return patientDAO.update(new Patient(patientDTO.getId(), patientDTO.getName(), patientDTO.getNic(), patientDTO.getAddress(), patientDTO.getContactNumber(), patientDTO.getEmail(), patientDTO.getDateOfBirth()));
    }

    @Override
    public boolean deletePatient(String id) {
        return patientDAO.delete(id);
    }

    @Override
    public List<PatientDTO> getAllPatient() {
        // Fetching all patients from the database
        List<Patient> patients = patientDAO.getAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();

        for (Patient patient : patients) {
            // Ensure that there are therapy sessions and registrations, and handle empty lists
            String therapyPrograms = (patient.getTherapySessions() != null && !patient.getTherapySessions().isEmpty())
                    ? String.valueOf(patient.getTherapySessions().get(0).getTherapyProgram())
                    : null;

            Double upfrontPayment = (patient.getRegistrations() != null && !patient.getRegistrations().isEmpty())
                    ? patient.getRegistrations().get(0).getUpfrontPayment()
                    : null;

            // Creating PatientDTO and adding to the list
            patientDTOList.add(new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getNic(),
                    patient.getAddress(),
                    patient.getContactNumber(),
                    patient.getEmail(),
                    patient.getDateOfBirth() != null ? Date.valueOf(patient.getDateOfBirth()) : null, // Converting String to Date if necessary
                    therapyPrograms,
                    upfrontPayment
            ));
        }

        return patientDTOList;
    }


}
