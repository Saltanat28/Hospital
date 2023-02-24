package peaksoft.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Department;
import peaksoft.repository.DepartmentRepository;
import peaksoft.service.DepartmentService;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveDepartment(Long hospitalId,Department department) {
        departmentRepository.saveDepartment(hospitalId, department);
    }

    @Override
    public List<Department> getAllDepartment(Long hospitalId) {
        return departmentRepository.getAllDepartment(hospitalId);
    }

    @Override
    public void updateDepartment(Department newDepartment, Long id) {
        departmentRepository.updateDepartment(newDepartment, id);
    }

    @Override
    public void deleteByDepartmentId(Long id) {
    departmentRepository.deleteByDepartmentId(id);
    }

    @Override
    public Department getByDepartmentId(Long id) {
        return departmentRepository.getByDepartmentId(id);
    }
}
