package lk.ijse.gdse.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String id;
    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;
    private String sessionId;
}


