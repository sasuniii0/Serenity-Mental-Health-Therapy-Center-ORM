package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Payment;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    double getTotalPaymentsByPatient(String patientId) throws Exception;
    String generateNextPaymentId() throws Exception;
    List<Payment> getPaymentsBySession(String sessionId) throws Exception;
    List<Payment> getPaymentsByPatient(String patientId) throws Exception;
}
