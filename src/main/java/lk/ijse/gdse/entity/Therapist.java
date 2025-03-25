package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Table (name = "Therapist")
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
    @JoinColumn(name = "program_id", nullable = false)
    private TherapyProgram therapyProgram;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions;
}
