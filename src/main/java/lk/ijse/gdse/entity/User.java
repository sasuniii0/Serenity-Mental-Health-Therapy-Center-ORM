package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

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
