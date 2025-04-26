package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionTM {
    private String sessionId;
    private LocalDate date;
    private String patientId;
    private String programId;
    private String therapistId;
    private String patient;
    private String program;
    private String therapist;
    private LocalDate sessionDate;
}
