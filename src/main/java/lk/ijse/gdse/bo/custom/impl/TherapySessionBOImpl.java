package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapySessionBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.entity.TherapySession;

import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    @Override
    public List<TherapySession> getAllTherapySessions() {
        return therapySessionDAO.getAll();
    }

    @Override
    public TherapySessionDTO searchTherapySession(String sessionId) {
        return null;
    }
}
