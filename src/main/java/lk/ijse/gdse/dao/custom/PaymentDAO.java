package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.entity.Payment;
import org.hibernate.Session;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    double getTotalPaymentsByPatient(String patientId) throws Exception;
    String generateNextPaymentId() throws Exception;
    List<Payment> getPaymentsBySession(String sessionId) throws Exception;
    List<Payment> getPaymentsByPatient(String patientId) throws Exception;

    String getNextPaymentId();

    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);

    boolean savePayment(Session session, Payment payment);
}
