package lk.ijse.gdse.dto.tm;

import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTM {
    private String registrationId;
    private String patientId;
    private String programId;
    private String patient;
    private String program;
    private double advancePayment;
    private LocalDate date;
}
