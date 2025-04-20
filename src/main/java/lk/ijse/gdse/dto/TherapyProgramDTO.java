package lk.ijse.gdse.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyProgramDTO {
    private String id;
    private String name;
    private double fee;
    private String duration;

    private List<Therapist> therapists;
    private List<Registration> registrations;

    public TherapyProgramDTO(String id, String name, double fee, String duration) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.duration = duration;
    }
}
