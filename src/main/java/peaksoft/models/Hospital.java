package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor

public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_gen", sequenceName = "hospital_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_gen")
    private Long id;
   private String name;
    private String address;
    private String image;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hospital")
    private List<Doctor>doctors;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Patient>patients;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hospital")
    private List<Department> departments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment>appointments;

    public Hospital(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

//    @Transient
//    private Long departmentId;
//    @Transient
//    private Long doctorId;
//    @Transient
//    private Long patientId;

    public void setDoctor(Doctor doctor){
        if(this.doctors==null) {
            this.doctors =new ArrayList<>();
        }
        this.doctors.add(doctor);
    }

    public void setDepartment(Department department){
        if(this.departments==null){
            this.departments = new ArrayList<>();
        }
        departments.add(department);
    }


    public void setPatient(Patient patient){
        if(patients==null){
            patients=new ArrayList<>();
        }
        patients.add(patient);
    }

}
