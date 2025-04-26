package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapySessionBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    PatientDAO patientDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    TherapistDAO therapistDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);

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
}
