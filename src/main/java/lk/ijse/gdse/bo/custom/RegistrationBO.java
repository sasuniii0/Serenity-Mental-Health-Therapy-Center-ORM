package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.RegistrationDTO;

import java.util.ArrayList;

public interface RegistrationBO extends SuperBO {
    boolean deleteRegistration(String regId);

    boolean saveRegistration(RegistrationDTO registrationDTO);

    boolean updateRegistration(RegistrationDTO registrationDTO);

    ArrayList<RegistrationDTO> getAllRegistrations();

    String getNextRegistrationId();

    double getAdvancePaymentByPatientAndProgram(String patientId, String programId);
}
