package lk.ijse.gdse.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse.entity.TherapyProgram;
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
    private List<TherapySession> therapySessions;

    private String therapyProgramId;
    private String therapyProgramName;

    public TherapistDTO(String id, String name, String email, String contactNumber, TherapyProgram therapyProgram) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.therapyProgramId = therapyProgram.getId();
        this.therapyProgramName = therapyProgram.getName();
    }

    public TherapistDTO(String id, String name, String email, String phone, String therapyProgramId, String therapyProgramName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = phone;
        this.therapyProgramId = therapyProgramId;
        this.therapyProgramName = therapyProgramName;
    }
}
