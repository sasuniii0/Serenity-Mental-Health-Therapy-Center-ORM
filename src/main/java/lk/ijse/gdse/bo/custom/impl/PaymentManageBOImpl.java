package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PaymentManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Payment;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;

public class PaymentManageBOImpl implements PaymentManageBO {

    PaymentDAO paymentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public boolean savePayment(PaymentDTO paymentDTO, String patientId, String program) throws Exception {
        return false;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {

            paymentDTOS.add(new PaymentDTO(
                    payment.getId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getRemainingAmount(),
                    payment.getStatus(),
                    payment.getTherapySession().getId()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public double getTotalPaymentsByPatient(String patientId) throws Exception {
        return 0;
    }

    @Override
    public String generateNextPaymentId() throws Exception {
        return "";
    }

    @Override
    public List<PaymentDTO> getPaymentsBySession(String sessionId) throws Exception {
        return List.of();
    }

    @Override
    public List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId) {
        return paymentDAO.getPaymentsByPatientAndProgram(patientId,programId);

    }

    @Override
    public String getNextPaymentId() {
        return paymentDAO.getNextPaymentId();
    }
}
