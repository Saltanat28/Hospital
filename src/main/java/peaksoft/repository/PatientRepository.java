package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.models.Patient;

import java.util.List;

@Repository
public interface PatientRepository {
    void savePatient(Long id,Patient patient);
    List<Patient> getAllPatients(Long hospitalId);
    void deletePatient(Long id);
    void updatePatient(Patient newPatient, Long id);
    Patient getByPatientId(Long id);

    List<Patient> findAllPatient();
}
