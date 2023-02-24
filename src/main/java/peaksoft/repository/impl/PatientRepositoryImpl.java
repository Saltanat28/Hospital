package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Hospital;
import peaksoft.models.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void savePatient(Long id, Patient patient) {
        Hospital hospital = entityManager.find(Hospital.class,id);
        hospital.setPatient(patient);
        patient.setHospital(hospital);
        entityManager.persist(patient);

    }

    @Override
    public List<Patient> getAllPatients(Long hospitalId) {
        return entityManager.createQuery("select p from Patient p where p.hospital.id=:id",Patient.class).
                setParameter("id",hospitalId).getResultList();

    }

    @Override
    public void deletePatient(Long id) {
        Patient patient1 = entityManager.find(Patient.class,id);
        entityManager.remove(patient1);

    }

    @Override
    public void updatePatient(Patient newPatient, Long id) {
        Patient patient2 = entityManager.find(Patient.class,id);
        patient2.setId(newPatient.getId());
        patient2.setFirstName(newPatient.getFirstName());
        patient2.setLastName(newPatient.getLastName());
        patient2.setEmail(newPatient.getEmail());
        patient2.setGender(newPatient.getGender());
        patient2.setPhoneNumber(newPatient.getPhoneNumber());
        entityManager.persist(patient2);

    }

    @Override
    public Patient getByPatientId(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public List<Patient> findAllPatient() {
        return entityManager.createQuery("select p from Patient p", Patient.class).getResultList();
    }
}