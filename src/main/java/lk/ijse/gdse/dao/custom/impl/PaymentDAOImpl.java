package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment entity) {
        return false;
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }
}
