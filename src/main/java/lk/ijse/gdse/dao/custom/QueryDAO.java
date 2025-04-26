package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.SuperDAO;
import lk.ijse.gdse.dto.PaymentDTO;

import java.util.List;

public interface    QueryDAO extends SuperDAO {
    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);

    List<Object[]> getAllEnrollments();
}
