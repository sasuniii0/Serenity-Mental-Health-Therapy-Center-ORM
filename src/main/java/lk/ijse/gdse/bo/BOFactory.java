package lk.ijse.gdse.bo;


import lk.ijse.gdse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory== null) ? boFactory
                = new BOFactory() : boFactory;
    }
    public enum BOTypes{
        PATIENT, PAYMENT,THERAPIST,THERAPY_PROGRAM,USER,THERAPY_SESSION
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperBO >T getBO(BOTypes boTypes){
        switch(boTypes){
            case USER -> {
                return (T) new UserBOImpl();
            }
            case PAYMENT -> {
                return (T) new PaymentManageBOImpl();
            }
            case PATIENT -> {
                return (T) new PatientManageBOImpl();
            }
            case THERAPIST -> {
                return (T) new TherapistManageBOImpl();
            }
            case THERAPY_PROGRAM -> {
                return (T) new TherapyProgramManageBOImpl();
            }
            case THERAPY_SESSION -> {
                return (T) new TherapySessionBOImpl();
            }
            default -> {
                return null;}
        }
    }
}
