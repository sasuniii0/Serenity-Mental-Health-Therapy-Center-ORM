package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.entity.User;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public void initializeDefaultUsers() {
        userDAO.initializeDefaultUsers();
    }

    @Override
    public User searchUserByEmail(String email) {
        return userDAO.searchUserByEmail(email);
    }

    @Override
    public User searchUserByAdminEmail(String adMail) {
        return userDAO.searchUserByAdminMail(adMail);
    }

    @Override
    public boolean updateUser(String selectedEmail, String password) {
        return false;
    }

}
