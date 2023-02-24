package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Department;
import peaksoft.models.Hospital;
import peaksoft.repository.DepartmentRepository;

import java.util.List;
@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Department> getAllDepartment(Long hospitalId) {
        return entityManager.createQuery("select d from Department  d where d.hospital.id=:id", Department.class)
                .setParameter("id",hospitalId).getResultList();

    }

    @Override
    public void saveDepartment(Long hospitalId,Department department) {
        Hospital hospital = entityManager.find(Hospital.class,hospitalId);
        hospital.setDepartment(department);
        department.setHospital(hospital);
        entityManager.persist(department);
    }

    @Override
    public void updateDepartment(Department newDepartment, Long id) {
        Department department = entityManager.find(Department.class,id);
        department.setId(newDepartment.getId());
        department.setName(newDepartment.getName());
        entityManager.persist(department);

    }

    @Override
    public void deleteByDepartmentId(Long id) {
        Department department = entityManager.find(Department.class, id);
    entityManager.remove(department);
    }

    @Override
    public Department getByDepartmentId(Long id) {
        return entityManager.find(Department.class, id);
    }
}
