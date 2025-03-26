package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;

import java.util.List;

public interface UserBO extends SuperBO {
    void initializeDefaultUsers();

    User searchUserByEmail(String email);

    User searchUserByAdminEmail(String adMail);

    boolean updateUser(String selectedEmail, String password);

    List<UserDTO> getAllUser();
}
