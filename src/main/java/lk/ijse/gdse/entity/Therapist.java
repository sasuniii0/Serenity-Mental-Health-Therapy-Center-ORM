package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Table (name = "therapist")
@Data
@AllArgsConstructor
@Entity
public class Therapist {
    @Id
    private String id;

    private String name;
    private String address;
    private int mobileNumber;
    private String nic;

    /* private String programId;*/

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;
}
