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
    private String programId;
    private String name;
    private String address;
    private int mobileNumber;
    private String nic;
}
