package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
    private String role;

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
