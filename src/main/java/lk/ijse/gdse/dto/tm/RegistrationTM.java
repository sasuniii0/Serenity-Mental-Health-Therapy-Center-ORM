package lk.ijse.gdse.dto.tm;

import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTM {
    private String id;
    private Patient patientName;
    private TherapyProgram therapyProgramName;
    private TherapyProgram therapyProgramFee;
    private double upfrontPayment;
}
