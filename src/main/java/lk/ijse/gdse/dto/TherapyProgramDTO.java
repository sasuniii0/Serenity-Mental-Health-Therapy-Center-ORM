package lk.ijse.gdse.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyProgramDTO {
    private String id;
    private String name;
    private double fee;
    private String duration;

}
