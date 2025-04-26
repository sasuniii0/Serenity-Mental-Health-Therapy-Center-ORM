package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapySessionDTO;

import java.util.ArrayList;
import java.util.Map;

public interface TherapySessionBO extends SuperBO {
    ArrayList<TherapySessionDTO> getAllTherapySessions();

    TherapySessionDTO searchTherapySession(String sessionId);

    TherapySessionDTO getSessionById(String sessionId);

    boolean deleteTherapySession(String sessionId);

    String getNextSessionId();

    ArrayList<TherapySessionDTO> getAllSessions();

    Map<String, Integer> getPatientSessionCounts();

/*
    Map<String, Integer> getPatientSessionCounts();
*/
}
