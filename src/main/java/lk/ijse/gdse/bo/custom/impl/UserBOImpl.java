package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return userDAO.update(new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole()));
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getFirstName(), user.getLastName()));
        }
        return userDTOS;
    }

    @Override
    public boolean deleteUser(String email) {
        return userDAO.delete(email);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextUserId();
    }

    @Override
    public void initialize() {
        createDefaultAdmin();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole()));

    }

    @Override
    public UserTM getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public Map<String, String> getRecentUserLogins() {
        return Map.of();
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
