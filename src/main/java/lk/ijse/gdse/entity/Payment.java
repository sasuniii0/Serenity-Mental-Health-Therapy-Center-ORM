package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table (name = "Payment")
public class Payment {
    @Id
    private String id;
    private double amount;
    private String date;

    @ManyToOne
    @JoinColumn(name = "therapy_session_id")
    private TherapySession therapySession;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;


}
