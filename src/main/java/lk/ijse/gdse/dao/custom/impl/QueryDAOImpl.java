package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.QueryDAO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDAOImpl implements QueryDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId) {
        Session session = factoryConfiguration.getSession();
        List<PaymentDTO> paymentDTOList = new ArrayList<>();

        try{
            session.beginTransaction();

            String hql =  "SELECT p FROM Payment p " +
                    "JOIN p.therapySession ts " +
                    "WHERE ts.patient.id = :patientId AND ts.therapyProgram.id = :programId";

            List<Payment> payments = session.createQuery(hql,Payment.class)
                    .setParameter("patientId",patientId)
                    .setParameter("programId",programId)
                    .getResultList();

            for(Payment payment : payments){
                PaymentDTO paymentDTO = new PaymentDTO(
                        payment.getId(),
                        payment.getDate(),
                        payment.getAmount(),
                        payment.getRemainingAmount(),
                        payment.getStatus(),
                        payment.getTherapySession().getId()
                );
                paymentDTOList.add(paymentDTO);
            }
            session.getTransaction().commit();
        }catch(Exception e){
            if(session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return paymentDTOList;
    }

    @Override
    public List<Object[]> getAllEnrollments() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String hql = "SELECT p.name, tp.programName, r.date " +
                    "FROM Registration r " +
                    "JOIN r.patient p " +
                    "JOIN r.therapyProgram tp " +
                    "ORDER BY p.name, r.date DESC";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public Map<String, Integer> getPatientSessionCounts() {
        Session session = factoryConfiguration.getSession();
        Map<String, Integer> patientSessionCounts = new HashMap<>();

        try {
            List<Object[]> results = session.createQuery("SELECT p.id, COUNT(ts.id) FROM Patient p LEFT JOIN TherapySession ts ON p.id GROUP BY p.id").getResultList();
            for (Object[] result : results) {
                String patientId = (String) result[0];
                Integer sessionCount = (Integer) result[1];
                patientSessionCounts.put(patientId, sessionCount);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return patientSessionCounts;
    }

    @Override
    public Map<String, Integer> getProgramEnrollmentCounts() {
        Session session = factoryConfiguration.getSession();
        String hql = "SELECT p.id, COUNT(r.id) FROM TherapyProgram p LEFT JOIN Registration r ON p.id  GROUP BY p.id";
        Query<Object[]> query = session.createQuery(hql);
        List<Object[]> results = query.getResultList();
        Map<String, Integer> enrollmentCounts = new HashMap<>();
        for (Object[] result : results) {
            String programId = (String) result[0];
            Integer enrollmentCount = (Integer) result[1];
            enrollmentCounts.put(programId, enrollmentCount);
        }
        return enrollmentCounts;
    }

    @Override
    public Map<String, Integer> getTherapistSessionCounts() {
        Session session = factoryConfiguration.getSession();
        Map<String, Integer> therapistSessionCounts = new HashMap<>();

        try {
            List<Object[]> results = session.createQuery("SELECT th.id, COUNT(ts.id) FROM Therapist th LEFT JOIN TherapySession ts on th.id GROUP BY th.id").getResultList();
            for (Object[] result : results) {
                String therapistId = (String) result[0];
                Integer sessionCount = (Integer) result[1];
                therapistSessionCounts.put(therapistId, sessionCount);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return therapistSessionCounts;
    }
}
