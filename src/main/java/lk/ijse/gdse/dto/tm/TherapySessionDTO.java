package lk.ijse.gdse.dto.tm;

import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionDTO {
    private String id;
    private String name;
    private LocalDate date;
    private String patientId;
    private String therapist;
    private String therapyProgram;
    private String status;
    private double amount;
}
