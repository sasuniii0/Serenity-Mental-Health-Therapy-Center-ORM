package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.SuperDAO;
import lk.ijse.gdse.dto.PaymentDTO;

import java.util.List;
import java.util.Map;

public interface    QueryDAO extends SuperDAO {
    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);

    List<Object[]> getAllEnrollments();

    Map<String, Integer> getTherapistSessionCounts();

    Map<String, Integer> getProgramEnrollmentCounts();

    Map<String, Integer> getPatientSessionCounts();
}
