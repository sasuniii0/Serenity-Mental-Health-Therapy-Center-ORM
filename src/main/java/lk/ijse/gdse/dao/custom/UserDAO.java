package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;
import lk.ijse.gdse.entity.User;

public interface UserDAO extends CrudDAO<User> {
    void initializeDefaultUsers();

    User searchUserByEmail(String email);

    User searchUserByAdminMail(String adMail);

    String getLastUserId();

    String generateNextUserId();

    UserTM getAllUsers();

    UserDTO getAllUser();
}
