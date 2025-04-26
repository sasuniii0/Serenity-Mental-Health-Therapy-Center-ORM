package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTM {
    private String id;
    private String sessionId;
    private String patientName;
    private String program;
    private String description;
    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;
}
