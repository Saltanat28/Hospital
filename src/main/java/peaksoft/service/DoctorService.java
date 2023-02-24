package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.models.Department;
import peaksoft.models.Doctor;

import java.util.List;
@Service
public interface DoctorService {
    void saveDoctors(Long id,Doctor doctor);
    List<Doctor> getAllDoctors(Long hospitalId);
    void updateDoctor(Doctor newDoctor, Long id);
    void deleteByDoctorId(Long id);
    Doctor getByDoctorId(Long id);
}
