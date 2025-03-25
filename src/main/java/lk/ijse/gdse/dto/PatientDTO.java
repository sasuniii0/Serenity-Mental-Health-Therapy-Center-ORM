package lk.ijse.gdse.dto;

import jakarta.persistence.Entity;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<TherapySession> therapySessions;
    private List<Registration> registrations;
}
