package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public boolean save(Payment entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Payment entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Payment payment = session.get(Payment.class, id);
            if (payment != null) {
                session.remove(payment);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Payment> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.createQuery("FROM Payment", Payment.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public double getTotalPaymentsByPatient(String patientId) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(p.amount) FROM Payment p WHERE p.id = :patientId",
                    Double.class);
            query.setParameter("patientId", patientId);
            Double total = query.uniqueResult();
            return total != null ? total : 0.0;
        } finally {
            session.close();
        }
    }

    @Override
    public String generateNextPaymentId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String lastId = session.createQuery(
                            "SELECT p.id FROM Payment p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastId != null) {
                int lastNum = Integer.parseInt(lastId.substring(1));
                return String.format("P%03d", lastNum + 1);
            }
            return "P001";
        } finally {
            session.close();
        }
    }

    @Override
    public List<Payment> getPaymentsBySession(String sessionId) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Payment> query = session.createQuery(
                    "FROM Payment p WHERE p.therapySession.id = :sessionId",
                    Payment.class);
            query.setParameter("sessionId", sessionId);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Payment> getPaymentsByPatient(String patientId) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Payment> query = session.createQuery(
                    "FROM Payment p WHERE p.id = :patientId",
                    Payment.class);
            query.setParameter("patientId", patientId);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String getNextPaymentId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            // Query to get the last payment ID
            String hql = "SELECT p.id FROM Payment p ORDER BY p.id DESC";
            Query<String> query = session.createQuery(hql, String.class)
                    .setMaxResults(1);

            String lastId = query.uniqueResult();

            if (lastId != null) {
                // Extract numeric part and increment
                String numericPart = lastId.replaceAll("\\D+", "");
                if (!numericPart.isEmpty()) {
                    int nextNum = Integer.parseInt(numericPart) + 1;
                    return String.format("P%03d", nextNum); // Format as P001, P002, etc.
                }
            }
            return "P001"; // Default first ID
        } catch (Exception e) {
            e.printStackTrace();
            return "P001"; // Fallback if error occurs
        }
    }

    /*@Override
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
    }*/

    @Override
    public boolean savePayment(Session session, Payment payment) {
        try{
            session.merge(payment);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
