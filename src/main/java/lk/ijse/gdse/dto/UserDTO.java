package lk.ijse.gdse.dto;

import lk.ijse.gdse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private String role;

    public UserDTO(String text, String text1, String text2, String text3, String text4, String text5, User.Role value) {
        this.id = text;
        this.firstName = text1;
        this.lastName = text2;
        this.email = text3;
        this.username = text4;
        this.password = text5;
        this.role = String.valueOf(value);
    }

    public UserDTO(String id, String firstName, String lastName, String email, String username, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(List<User> users) {
        for (User user : users) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.role = String.valueOf(user.getRole());
        }
    }

    public UserDTO(String id, String firstName, String lName, String email, String username, String hashedPassword, String confirmPassword, User.Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lName;
        this.email = email;
        this.username = username;
        this.password = hashedPassword;
        this.confirmPassword = confirmPassword;
        this.role = String.valueOf(role);
    }
}
