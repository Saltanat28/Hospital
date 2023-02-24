package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Patient;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }
    @Override
    public void savePatient(Long hospitalId,Patient patient) {
        patientRepository.savePatient(hospitalId,patient);
    }
    @Override
    public List<Patient> getAllPatients(Long hospitalId) {
            return patientRepository.getAllPatients(hospitalId);
    }
    @Override
    public void deletePatient(Long id) {
    patientRepository.deletePatient(id);
    }
    @Override
    public void updatePatient(Patient newPatient, Long id) {
    patientRepository.updatePatient(newPatient, id);
    }
    @Override
    public Patient getByPatientId(Long id) {
        return patientRepository.getByPatientId(id);
    }
}
