package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table (name = "TherapyProgram")
@Data
@Entity
public class TherapyProgram {
    @Id
    private String id;
    private String name;
    private double fee;
    private String duration;

}
