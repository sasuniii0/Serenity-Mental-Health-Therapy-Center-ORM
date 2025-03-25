package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table (name = "TherapySession")
@AllArgsConstructor
@Data
@Entity
public class TherapySession {
    @Id
    private String id;
    private String name;
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "therapy_program_id")
    private TherapyProgram therapyProgram;
}
