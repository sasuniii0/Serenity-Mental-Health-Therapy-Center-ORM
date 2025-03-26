package lk.ijse.gdse.dto.tm;

import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.Therapist;
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
}
