package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table (name = "Therapist")
@Data
@AllArgsConstructor
@Entity
public class Therapist {
    @Id
    private String id;
    private String name;
    private String email;
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "therapy_program_id")
    private TherapyProgram therapyProgram;

}
