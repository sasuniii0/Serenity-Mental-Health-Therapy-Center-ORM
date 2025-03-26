package lk.ijse.gdse.dto.tm;

import lk.ijse.gdse.entity.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistDTO {
    private String id;
    private String name;
    private String email;
    private String contactNumber;
    private String therapyProgramId;
    private String therapyProgramName;
}
