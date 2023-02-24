package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_gen", sequenceName = "patient_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_gen")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;

    private String  gender;
    private String email;
//    @Transient
//    private Long hospitalId;
    @ManyToOne (cascade = {CascadeType.DETACH,
            REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private Hospital hospital;
    @OneToMany(mappedBy = "patient", cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch =FetchType.EAGER)
    private List<Appointment> appoitmentList;


}
