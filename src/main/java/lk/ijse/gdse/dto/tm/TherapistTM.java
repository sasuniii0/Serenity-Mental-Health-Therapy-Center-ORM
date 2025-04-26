package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TherapistTM {
    private String therapistId;
    private String name;
    private String address;
    private String nic;
    private int mobile;
    private String program;
    private String programId;
}
