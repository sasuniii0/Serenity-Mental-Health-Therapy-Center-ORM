package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.TherapySession;

import java.util.List;

public interface TherapySessionBO extends SuperBO {
    List<TherapySession> getAllTherapySessions();

    TherapySessionDTO searchTherapySession(String sessionId);
}
