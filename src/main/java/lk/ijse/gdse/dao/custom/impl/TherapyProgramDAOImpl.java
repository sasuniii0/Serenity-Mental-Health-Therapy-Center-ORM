package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.entity.TherapyProgram;

import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public boolean save(TherapyProgram entity) {
        return false;
    }

    @Override
    public boolean update(TherapyProgram entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<TherapyProgram> getAll() {
        return List.of();
    }
}
