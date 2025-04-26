package lk.ijse.gdse.dto.tm;

import lk.ijse.gdse.entity.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTM {
    private String id;  // Custom ID (Auto-generated)
    private String userName; // Username'
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public UserTM(List<User> users) {
        for (User user : users) {
            this.id = user.getId();
            this.userName = user.getUsername();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.role = user.getRole();
        }
    }
}
