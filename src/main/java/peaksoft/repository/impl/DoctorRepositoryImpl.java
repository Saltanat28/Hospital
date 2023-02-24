package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import peaksoft.models.Doctor;
import peaksoft.models.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.List;
@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveDoctor(Long hospitalId,Doctor doctor) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.setDoctor(doctor);
        doctor.setHospital(hospital);
        entityManager.persist(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors(Long hospitalId) {
        return entityManager.createQuery("select d from Doctor d where d.hospital.id=:id", Doctor.class).
                setParameter("id", hospitalId).getResultList();
    }

    @Override
    public void updateDoctor(Doctor newDoctor, Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setId(newDoctor.getId());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setImage(newDoctor.getImage());
        doctor.setPosition(newDoctor.getPosition());
    }

    @Override
    public void deleteByDoctorId(Long id) {
     Doctor doctor1 = entityManager.find(Doctor.class,id);
    entityManager.remove(doctor1);
    }

    @Override
    public Doctor getByDoctorId(Long id) {
        return entityManager.find(Doctor.class,id);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return entityManager.createQuery("select d from Doctor d", Doctor.class).getResultList();
    }
}
