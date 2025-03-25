package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Table (name = "Registration")
@NoArgsConstructor
@Data
public class Registration {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "therapy_program_id")
    private TherapyProgram therapyProgram;

    private double upfrontPayment;

}
