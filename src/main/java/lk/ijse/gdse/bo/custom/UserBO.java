package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;
import lk.ijse.gdse.entity.User;

import java.util.List;
import java.util.Map;

public interface UserBO extends SuperBO {
    void initializeDefaultUsers();

    User searchUserByEmail(String email);

    User searchUserByAdminEmail(String adMail);

    boolean updateUser(UserDTO userDTO);

    List<UserDTO> getAllUser();

    boolean deleteUser(String email);

    boolean addUser(UserDTO userDTO);

    String generateNextUserId();

    void initialize();

    boolean saveUser(UserDTO userDTO);

    UserTM getAllUsers();

    Map<String, String> getRecentUserLogins();
}
