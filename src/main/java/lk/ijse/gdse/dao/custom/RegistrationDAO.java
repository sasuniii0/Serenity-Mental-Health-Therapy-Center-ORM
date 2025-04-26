package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SuperDAO;
import lk.ijse.gdse.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration> {

    String getNextId();

    double getAdvancePaymentByPatientAndProgram(String patientId, String programId);
}
