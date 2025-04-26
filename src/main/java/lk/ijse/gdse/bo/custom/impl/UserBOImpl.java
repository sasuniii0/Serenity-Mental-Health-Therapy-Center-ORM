package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.PasswordUtil;

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

    @Override
    public void initialize() {
        createDefaultAdmin();
    }

    PasswordUtil passwordUtil;

    private void createDefaultAdmin() {
        try {
            // Check if admin already exists
            User existingAdmin = searchUserByEmail("admin@gmail.com");
            if (existingAdmin != null) return;

            // Create default admin user with BCrypt hashed password
            UserDTO admin = new UserDTO();
            admin.setId("U001");
            admin.setFirstName("System");
            admin.setLastName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setUsername("admin");
            admin.setPassword(PasswordUtil.hashPassword("1234")); // BCrypt hashing
            admin.setRole("admin");

            addUser(admin);
            System.out.println("Default admin user created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to create default admin user");
        }
    }
}
