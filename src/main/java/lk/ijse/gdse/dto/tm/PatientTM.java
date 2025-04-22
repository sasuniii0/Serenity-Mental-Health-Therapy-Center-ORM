package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date dateOfBirth;
    private String therapyPrograms;
    private String upfrontPayment;

    public PatientTM(String id, String name, String nic, String address, String contactNumber, String email, String dateOfBirth, String string, String string1) {
    }
}
