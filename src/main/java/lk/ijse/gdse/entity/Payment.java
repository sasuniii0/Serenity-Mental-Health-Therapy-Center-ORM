package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table (name = "payment")
public class Payment {
    @Id
    private String id;
    private double amount;
    private String date;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

}
