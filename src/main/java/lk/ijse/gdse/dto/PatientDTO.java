package lk.ijse.gdse.dto;

import jakarta.persistence.Entity;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private int mobileNumber;
    private String nic;
    private String gender;

}
