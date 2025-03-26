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
    private double amount;
    private LocalDate date;
    private String patientId;
    private String patientName;
    private String therapyProgram;
    private double balance;
}
