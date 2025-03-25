package lk.ijse.gdse.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse.entity.TherapyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistDTO {
    private String id;
    private String name;
    private String email;
    private String contactNumber;
    private TherapyProgram therapyProgram;
}
