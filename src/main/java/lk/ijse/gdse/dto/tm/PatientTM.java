package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientTM {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String contactNumber;
    private String email;
    private String dateOfBirth;
    private String therapyPrograms;
    private String upfrontPayment;
}
