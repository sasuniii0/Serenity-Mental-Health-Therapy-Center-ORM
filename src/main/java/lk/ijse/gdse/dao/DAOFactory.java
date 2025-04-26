package lk.ijse.gdse.dao;

import lk.ijse.gdse.dao.custom.impl.*;


public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)
                ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        PATIENT,THERAPIST,THERAPY_PROGRAM,THERAPY_SESSION,PAYMENT,USER,QUERY,REGISTRATION
    }
    @SuppressWarnings("unchecked")
    public <T extends SuperDAO >T getDAO(DAOTypes daoTypes){
        switch(daoTypes){
            case USER -> {
                return (T) new UserDAOImpl();
            }
            case PAYMENT -> {
                return (T) new PaymentDAOImpl();
            }
            case PATIENT -> {
                return (T) new PatientDAOImpl();
            }
            case THERAPIST -> {
                return (T) new TherapistDAOImpl();
            }
            case THERAPY_PROGRAM -> {
                return (T) new TherapyProgramDAOImpl();
            }
            case THERAPY_SESSION -> {
                return (T) new TherapySessionDAOImpl();
            }
            case QUERY -> {
                return (T) new QueryDAOImpl();
            }
            case REGISTRATION -> {
                return (T) new RegistrationDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
