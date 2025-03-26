package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTM {
    private String id;  // Custom ID (Auto-generated)

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String action;

}
