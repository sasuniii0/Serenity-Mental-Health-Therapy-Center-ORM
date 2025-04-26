package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.RegistrationBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dao.custom.RegistrationDAO;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dto.RegistrationDTO;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    PatientDAO patientDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    @Override
    public boolean deleteRegistration(String regId) {
        return registrationDAO.delete(regId);
    }

    @Override
    public boolean saveRegistration(RegistrationDTO registrationDTO) {
        TherapyProgram therapyProgram = therapyProgramDAO.getProgramId(registrationDTO.getProgramId());
        Patient patient = patientDAO.getPatientId(registrationDTO.getPatientId());

        if (therapyProgram == null || patient == null) {
            throw new RuntimeException("Program  or patient not found for ID: ");
        }
        Registration registration = new Registration(
                registrationDTO.getId(),
                registrationDTO.getDate(),
                registrationDTO.getAdvancePayment(),
                patient,
                therapyProgram
        );
        return registrationDAO.save(registration);
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) {
        TherapyProgram therapyProgram = therapyProgramDAO.getProgramId(registrationDTO.getProgramId());
        Patient patient = patientDAO.getPatientId(registrationDTO.getPatientId());

        if (therapyProgram == null || patient == null) {
            throw new RuntimeException("Program  or patient not found for ID: ");
        }
        Registration registration = new Registration(
                registrationDTO.getId(),
                registrationDTO.getDate(),
                registrationDTO.getAdvancePayment(),
                patient,
                therapyProgram
        );
        return registrationDAO.update(registration);
    }

    @Override
    public ArrayList<RegistrationDTO> getAllRegistrations() {
        List<Registration> registrations = registrationDAO.getAll();
        ArrayList<RegistrationDTO> registrationDTOS = new ArrayList<>();

        for (Registration registration : registrations) {
            registrationDTOS.add(new RegistrationDTO(
                    registration.getId(),
                    registration.getPatient().getId(),
                    registration.getTherapyProgram().getId(),
                    registration.getDate(),
                    registration.getAdvancePayment()
            ));
        }
        return registrationDTOS;
    }

    @Override
    public String getNextRegistrationId() {
        return registrationDAO.getNextId();
    }

    @Override
    public double getAdvancePaymentByPatientAndProgram(String patientId, String programId) {
        return registrationDAO.getAdvancePaymentByPatientAndProgram(patientId,programId);    }
}
