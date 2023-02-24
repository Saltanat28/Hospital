package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.models.Department;
import peaksoft.models.Doctor;

import javax.print.Doc;
import java.util.List;
@Repository
public interface DoctorRepository {
   void saveDoctor(Long hospitalId,Doctor doctor);
    List<Doctor> getAllDoctors(Long hospitalId);
    void updateDoctor(Doctor newDoctor, Long id);
    void deleteByDoctorId(Long id);
    Doctor getByDoctorId(Long id);

    List<Doctor> findAllDoctors();
}
