package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PaymentDTO;

import java.util.List;

public interface PaymentManageBO extends SuperBO {
    boolean savePayment(PaymentDTO paymentDTO, String patientId, String program) throws Exception;

    List<PaymentDTO> getAllPayments();

    double getTotalPaymentsByPatient(String patientId) throws Exception;

    String generateNextPaymentId() throws Exception;

    List<PaymentDTO> getPaymentsBySession(String sessionId) throws Exception;

    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);

    String getNextPaymentId();
}
