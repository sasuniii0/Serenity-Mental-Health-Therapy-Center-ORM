package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    private String id;  // Custom ID (Auto-generated)

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN or RECEPTIONIST

    public enum UserRole {
        ADMIN, RECEPTIONIST
    }

    @PrePersist
    public void generateUserId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = generateNextUserId();
        }
    }

    private String generateNextUserId() {
        return null; // Placeholder, actual logic will be in the bo
    }
}
