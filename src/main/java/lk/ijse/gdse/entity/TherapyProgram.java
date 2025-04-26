package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table (name = "therapy_program")
@Data
@Entity
public class TherapyProgram {
    @Id
    private String id;

    private String programName;
    private String duration;
    private double fee;
}
