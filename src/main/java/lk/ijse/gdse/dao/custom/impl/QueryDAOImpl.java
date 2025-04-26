package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.QueryDAO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
}
