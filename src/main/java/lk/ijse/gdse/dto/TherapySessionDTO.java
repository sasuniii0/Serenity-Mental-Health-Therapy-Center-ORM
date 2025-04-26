package lk.ijse.gdse.dto;

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
    private String patientId;
    private String programId;
    private String therapistId;
    private String description;
    private LocalDate date;
    private LocalDate sessionDate;
}
