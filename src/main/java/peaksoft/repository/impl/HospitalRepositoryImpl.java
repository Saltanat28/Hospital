package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Hospital saveHospital(Hospital hospital) {
        entityManager.persist(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return entityManager.createQuery("select h from  Hospital  h", Hospital.class).getResultList();
    }
    @Override
    public void deleteByHospitalId(Long id) {
        Hospital existHospital = entityManager.find(Hospital.class, id);
        entityManager.remove(existHospital);
    }
    @Override
    public Hospital getByHospitalId(Long id) {
       return entityManager.find(Hospital.class,id);
    }


}
