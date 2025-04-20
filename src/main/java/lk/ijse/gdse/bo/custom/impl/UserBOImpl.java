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
    public boolean updateUser(UserDTO userDTO) {
        try {
            User user = new User(
                    userDTO.getId(),
                    userDTO.getEmail(),
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getPassword(),
                    User.UserRole.valueOf(userDTO.getRole())  // Assuming 'role' is a String
            );
            return userDAO.update(user);  // Call DAO's update method to persist the user
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    @Override
    public boolean deleteUser(String email) {
        return userDAO.delete(email);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
            try {
                // Convert UserDTO to User entity
                User user = new User(
                        userDTO.getId(),
                        userDTO.getEmail(),
                        userDTO.getFirstName(),
                        userDTO.getLastName(),
                        userDTO.getPassword(),
                        User.UserRole.valueOf(userDTO.getRole())  // Assuming 'role' is a String
                );

                return userDAO.save(user);  // Call DAO's save method to persist the user
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
    }

    @Override
    public String generateNextUserId() {
        String lastId = userDAO.getLastUserId(); // returns something like "U-3"

        if (lastId != null && lastId.matches("U-\\d+")) {
            int lastNumber = Integer.parseInt(lastId.split("-")[1]);
            int nextNumber = lastNumber + 1;
            return "U-" + nextNumber;
        } else {
            return "U-1"; // If no users exist
        }
    }

}
