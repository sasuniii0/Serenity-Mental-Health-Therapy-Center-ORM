package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Table (name = "therapy_session")
@AllArgsConstructor
@Data
@Entity
public class TherapySession {
    @Id
    private String id;

    private String description;
    private LocalDate date;
    private LocalDate sessionDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

}
