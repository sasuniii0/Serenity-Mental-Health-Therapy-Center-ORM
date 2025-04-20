package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Table (name = "therapist")
@Data
@AllArgsConstructor
@Entity
public class Therapist {
    @Id
    private String id;
    private String name;
    private String email;
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions;

    public Therapist(String id, String name, String email, String contactNumber, String therapyProgramId, String therapyProgramName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.therapyProgram = new TherapyProgram(therapyProgramId, therapyProgramName);
    }
}
