package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userDAO.getAll(); // Retrieve all users from the database or any other source>
        List<UserDTO> userDTOs = new ArrayList<>(); // Convert User objects to UserDTO>

        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole()
            );
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

}
