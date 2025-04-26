package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.TherapySession;
import org.hibernate.Session;

import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public boolean save(TherapySession entity) {
        return false;
    }

    @Override
    public boolean update(TherapySession entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<TherapySession> getAll() {
        return List.of();
    }

    @Override
    public TherapySession search(String sessionId) {
        return null;
    }

    @Override
    public boolean saveSessionWithPayment(Session session, TherapySession therapySession) {
        return false;
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public TherapySessionDTO getSessionById(String sessionId) {
        return null;
    }
}
