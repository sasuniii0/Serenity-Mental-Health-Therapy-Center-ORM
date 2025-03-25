package lk.ijse.gdse.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse.entity.Registration;
import lk.ijse.gdse.entity.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String id;
    private double amount;
    private String date;
    private TherapySession therapySession;
    private Registration registration;
}
