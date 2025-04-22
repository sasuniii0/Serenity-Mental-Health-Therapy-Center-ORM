package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient   {
    @Id
    private String id;
    private String name;
    private String nic;
    private String address;
    private String contactNumber;
    private String email;
    private String dateOfBirth;

    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private List<TherapySession> therapySessions;

    public Patient(String id, String name, String nic, String address, String contactNumber, String email, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.dateOfBirth = String.valueOf(dateOfBirth);
    }

    public Patient(String id, String name, String nic, String address, String contactNumber, String email, LocalDate dateOfBirth, String therapyPrograms, Double upfrontPayment) {
    }
}
