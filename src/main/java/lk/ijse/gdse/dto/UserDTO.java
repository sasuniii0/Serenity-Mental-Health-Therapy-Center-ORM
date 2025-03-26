package lk.ijse.gdse.dto;

import lk.ijse.gdse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String id;  // Custom ID (Auto-generated)

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public UserDTO(String id, String email, String password, String firstName, String lastName, User.UserRole role) {
    }
}
