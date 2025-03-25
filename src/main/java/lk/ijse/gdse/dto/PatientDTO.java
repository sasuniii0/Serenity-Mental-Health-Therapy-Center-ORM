package lk.ijse.gdse.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String contactNumber;
    private String email;
    private String dateOfBirth;
}
