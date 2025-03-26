package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.entity.User;

public interface UserBO extends SuperBO {
    void initializeDefaultUsers();

    User searchUserByEmail(String email);

    User searchUserByAdminEmail(String adMail);
}
