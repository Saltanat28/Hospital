package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.models.Department;

import java.util.List;
@Service
public interface DepartmentService {
    void saveDepartment(Long hospitalId,Department department);
    List<Department> getAllDepartment(Long hospitalId);
    void updateDepartment(Department newDepartment, Long id);
    void deleteByDepartmentId(Long id);
    Department getByDepartmentId(Long id);
}
