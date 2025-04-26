package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PaymentManageBO;
import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.*;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Payment;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PaymentManageBOImpl implements PaymentManageBO {

    PaymentDAO paymentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY); //QueryDAO


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
        return queryDAO.getPaymentsByPatientAndProgram(patientId,programId);

    }

    @Override
    public String getNextPaymentId() {
        return paymentDAO.getNextPaymentId();
    }


    @Override
    public Map<String, Double> getMonthlyRevenue() {
        Map<String, Double> monthlyRevenue = new LinkedHashMap<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // HQL query to get monthly revenue
            String hql = "SELECT FUNCTION('DATE_FORMAT', p.date, '%Y-%m') as month, " +
                    "SUM(p.amount) as total " +
                    "FROM Payment p " +
                    "WHERE p.date >= FUNCTION('DATE_SUB', CURRENT_DATE(), 12, 'MONTH') " +
                    "GROUP BY FUNCTION('DATE_FORMAT', p.date, '%Y-%m') " +
                    "ORDER BY date DESC";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                String month = (String) result[0];
                Double total = (Double) result[1];
                monthlyRevenue.put(month, total);
            }

            transaction.commit();

            // Ensure we have all months, even those with zero revenue
            LocalDate now = LocalDate.now();
            for (int i = 11; i >= 0; i--) {
                LocalDate month = now.minusMonths(i);
                String monthKey = month.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                monthlyRevenue.putIfAbsent(monthKey, 0.0);
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch monthly revenue", e);
        } finally {
            session.close();
        }

        return monthlyRevenue;
    }


    @Override
    public List<Object[]> getPendingPayments() {
        return paymentDAO.getPendingPayments();
    }
}
