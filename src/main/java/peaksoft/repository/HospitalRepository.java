package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.models.Hospital;

import java.util.List;
@Repository
public interface HospitalRepository {
     Hospital saveHospital(Hospital hospital);
    List<Hospital> getAllHospital();
     void deleteByHospitalId(Long id);
      Hospital getByHospitalId(Long id);


}
