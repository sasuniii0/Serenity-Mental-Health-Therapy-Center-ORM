package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.ScheduleAppoinmentBO;
import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class ScheduleAppoinmentBOImpl implements ScheduleAppoinmentBO {

    TherapySessionDAO therapySessionDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    PaymentDAO paymentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    PatientDAO patientDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    TherapistManageBO therapistManageBO = (TherapistManageBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);

    @Override
    public boolean saveSessionAndPayment(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(therapySessionDTO.getId());
        System.out.println(paymentDTO.getId());

        try{

//            Optional<TherapySession> optionalTherapySession = therapySessionDAO.findByPK(sessionId);
//            if(optionalTherapySession.isPresent()){
//                transaction.rollback();
//                return false;
//            }

//            Optional<Payment> optionalPayment = paymentDAO.findByPK(paymentId);
//            if(optionalPayment.isPresent()){
//                transaction.rollback();
//                return false;
//            }

            String programId = therapySessionDTO.getProgramId();

            Optional<TherapyProgram> optionalTherapyProgram = therapyProgramDAO.findByPK(programId);
            if(optionalTherapyProgram.isEmpty()){
                transaction.rollback();
                return false;
            }

            String patientId = therapySessionDTO.getPatientId();

            Optional<Patient> optionalPatient = patientDAO.findByPK(patientId);
            if(optionalPatient.isEmpty()){
                transaction.rollback();
                return false;
            }

            String therapistId = therapySessionDTO.getTherapistId();

            Optional<Therapist> optionalTherapist = therapistManageBO.findByPK(therapistId);
            if(optionalTherapist.isEmpty()){
                transaction.rollback();
                return false;
            }

            TherapySession therapySession = new TherapySession();
            therapySession.setId(therapySessionDTO.getId());
            therapySession.setDate(therapySessionDTO.getDate());
            therapySession.setSessionDate(therapySessionDTO.getSessionDate());
            therapySession.setDescription(therapySessionDTO.getDescription());
            therapySession.setTherapyProgram(optionalTherapyProgram.get());
            therapySession.setPatient(optionalPatient.get());
            therapySession.setTherapist(optionalTherapist.get());

            boolean isSessionSaved =  therapySessionDAO.saveSessionWithPayment(session , therapySession);
            if(!isSessionSaved){
                transaction.rollback();
                return false;
            }

            Payment payment = new Payment();
            payment.setId(paymentDTO.getId());
            payment.setDate(paymentDTO.getDate());
            payment.setAmount(paymentDTO.getAmount());
            payment.setStatus(paymentDTO.getStatus());
            payment.setTherapySession(therapySession);
            payment.setRemainingAmount(paymentDTO.getRemainingAmount());

            boolean isPaymentSaved = paymentDAO.savePayment(session , payment);
            if(!isPaymentSaved){
                transaction.rollback();
                return false;
            }

            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }



    }

    @Override
    public boolean updateSessionAndPayment(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO) {
        return false;
    }
}
