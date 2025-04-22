package lk.ijse.gdse.dto;

import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionDTO {
    private String id;
    private String name;
    private String date;
    private String patient;
    private String therapist;
    private String therapyProgram;
}
