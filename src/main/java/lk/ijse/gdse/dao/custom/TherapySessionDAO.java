package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.TherapySession;
import org.hibernate.Session;

import java.util.Map;

public interface TherapySessionDAO extends CrudDAO<TherapySession> {
    TherapySession search(String sessionId);

    boolean saveSessionWithPayment(Session session, TherapySession therapySession);

    String getNextId();

    TherapySessionDTO getSessionById(String sessionId);

/*
    Map<String, Integer> getPatientSessionCounts();
*/
}
