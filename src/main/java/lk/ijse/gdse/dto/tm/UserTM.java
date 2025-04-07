package lk.ijse.gdse.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTM {
    private String id;  // Custom ID (Auto-generated)

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

}
