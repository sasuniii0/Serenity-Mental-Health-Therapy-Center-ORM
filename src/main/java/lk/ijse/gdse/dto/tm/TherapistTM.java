package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TherapistTM {
    private String id;
    private String name;
    private String email;
    private String contactNumber;
    private String therapyProgramId;
    private String therapyProgramName;
}
