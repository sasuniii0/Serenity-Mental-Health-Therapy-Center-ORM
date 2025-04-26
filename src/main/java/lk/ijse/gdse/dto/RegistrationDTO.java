package lk.ijse.gdse.dto;

import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    private String id;
    private String patientId;
    private String programId;
    private LocalDate date;
    private double advancePayment;
}
