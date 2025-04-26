package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapySessionBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.*;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    PatientDAO patientDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    TherapistDAO therapistDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY); //QueryDAO

    @Override
    public ArrayList<TherapySessionDTO> getAllTherapySessions() {
        List<TherapySession> therapySessions = therapySessionDAO.getAll();
        ArrayList<TherapySessionDTO> therapySessionDTOS = new ArrayList<>();

        for (TherapySession therapySession : therapySessions) {
            therapySessionDTOS.add(new TherapySessionDTO(
                    therapySession.getId(),
                    therapySession.getPatient().getId(),
                    therapySession.getTherapyProgram().getId(),
                    therapySession.getTherapist().getId(),
                    therapySession.getDescription(),
                    therapySession.getDate(),
                    therapySession.getSessionDate()
            ));
        }
        return therapySessionDTOS;
    }

    @Override
    public TherapySessionDTO searchTherapySession(String sessionId) {
        return null;
    }

    @Override
    public TherapySessionDTO getSessionById(String sessionId) {
        return therapySessionDAO.getSessionById(sessionId);
    }

    @Override
    public boolean deleteTherapySession(String sessionId) {
        return therapySessionDAO.delete(sessionId);
    }

    @Override
    public String getNextSessionId() {
        return therapySessionDAO.getNextId();
    }

    @Override
    public ArrayList<TherapySessionDTO> getAllSessions() {
        List<TherapySession> therapySessions = therapySessionDAO.getAll();
        ArrayList<TherapySessionDTO> therapySessionDTOS = new ArrayList<>();

        for (TherapySession therapySession : therapySessions) {
            therapySessionDTOS.add(new TherapySessionDTO(
                    therapySession.getId(),
                    therapySession.getPatient().getId(),
                    therapySession.getTherapyProgram().getId(),
                    therapySession.getTherapist().getId(),
                    therapySession.getDescription(),
                    therapySession.getDate(),
                    therapySession.getSessionDate()
            ));
        }
        return therapySessionDTOS;
    }

    @Override
    public Map<String, Integer> getPatientSessionCounts() {
        return queryDAO.getPatientSessionCounts();
    }
}
