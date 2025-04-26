package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lk.ijse.gdse.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table (name = "payment")
public class Payment {
    @Id
    private String id;

    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;

    @OneToOne
    @JoinColumn(name = "session_id")
    private TherapySession therapySession;
}
