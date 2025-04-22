package lk.ijse.gdse.dto;

import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    private String id;
    private String patientId;
    private String therapyProgramId;
    private double upfrontPayment;
}
