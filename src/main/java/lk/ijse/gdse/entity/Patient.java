package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Patient")
public class Patient {
    @Id
    private String id;
    private String name;
    private String nic;
    private String address;
    private String contactNumber;
    private String email;
    private String dateOfBirth;

}
