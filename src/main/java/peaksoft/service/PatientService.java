package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.models.Patient;

import java.util.List;
@Service
public interface PatientService {
    void savePatient(Long hospitalId,Patient patient);
    List<Patient> getAllPatients(Long hospitalId);
    void deletePatient(Long id);
    void updatePatient(Patient newPatient, Long id);
    Patient getByPatientId(Long id);
}
