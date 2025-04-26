package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.RegistrationDAO;
import lk.ijse.gdse.entity.Registration;

import java.util.List;

public class RegistrationImplDAO implements RegistrationDAO {
    @Override
    public boolean save(Registration entity) {
        return false;
    }

    @Override
    public boolean update(Registration entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Registration> getAll() {
        return List.of();
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public double getAdvancePaymentByPatientAndProgram(String patientId, String programId) {
        return 0;
    }
}
