package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean save(Patient entity) {
        return false;
    }

    @Override
    public boolean update(Patient entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Patient> getAll() {
        return List.of();
    }
}
