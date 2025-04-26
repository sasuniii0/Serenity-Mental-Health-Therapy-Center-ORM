package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientTM {
    private String patientId;
    private String patientName;
    private String address;
    private String nic ;
    private String email;
    private int mobile;
    private String gender;

}
