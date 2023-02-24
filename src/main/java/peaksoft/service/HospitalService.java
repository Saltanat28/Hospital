package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.models.Hospital;

import java.util.List;
@Service
public interface HospitalService {
    public Hospital saveHospital(Hospital hospital);
    public List<Hospital> getAllHospital();
    public void updateHospital( Hospital newHospital, Long id);
    public void deleteByHospitalId(Long id);
    public Hospital getByHospitalId(Long id);

}
