package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.models.Department;

import java.util.List;
@Repository
public interface DepartmentRepository {
     void saveDepartment(Long hospitalId,Department department);
     List<Department> getAllDepartment(Long hospitalId);
     void updateDepartment(Department newDepartment, Long id);
     void deleteByDepartmentId(Long id);
      Department getByDepartmentId(Long id);
}
