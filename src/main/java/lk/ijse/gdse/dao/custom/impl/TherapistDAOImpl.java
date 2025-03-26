package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.entity.Therapist;

import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist entity) {
        return false;
    }

    @Override
    public boolean update(Therapist entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Therapist> getAll() {
        return List.of();
    }
}
