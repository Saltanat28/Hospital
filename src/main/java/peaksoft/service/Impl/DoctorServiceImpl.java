package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.models.Doctor;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public void saveDoctors(Long id,Doctor doctor) {
        Doctor doctor1 = new Doctor();
        doctor1.setId(doctor.getId());
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setHospital(hospitalRepository.getByHospitalId(id));
        doctorRepository.saveDoctor(id,doctor1);
    }

    @Override
    public List<Doctor> getAllDoctors(Long hospitalId) {
        return doctorRepository.getAllDoctors(hospitalId);
    }

    @Override
    public void updateDoctor(Doctor newDoctor, Long id) {
    doctorRepository.updateDoctor(newDoctor, id);
    }

    @Override
    public void deleteByDoctorId(Long id) {
    doctorRepository.deleteByDoctorId(id);
    }

    @Override
    public Doctor getByDoctorId(Long id) {
        return doctorRepository.getByDoctorId(id);
    }
}