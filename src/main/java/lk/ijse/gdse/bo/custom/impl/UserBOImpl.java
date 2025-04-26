package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {


    @Override
    public void initializeDefaultUsers() {

    }

    @Override
    public User searchUserByEmail(String email) {
        return null;
    }

    @Override
    public User searchUserByAdminEmail(String adMail) {
        return null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public List<UserDTO> getAllUser() {
        return List.of();
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public String generateNextUserId() {
        return "";
    }
}
