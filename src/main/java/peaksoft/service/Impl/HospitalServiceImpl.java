package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.models.Hospital;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    public Hospital saveHospital(Hospital hospital) {
        hospitalRepository.saveHospital(hospital);
//        Hospital hospital1 = new Hospital();
//        hospital1.setId(hospital.getId());
//        hospital1.setName(hospital.getName());
//        hospital1.setAddress(hospital.getAddress());
//        hospital1.setImage(hospital.getImage());
//        hospital1.setDepartments(departmentRepository.getByDepartmentId(hospital.getDepartmentId()));
//        hospitalRepository.saveHospital(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalRepository.getAllHospital();
    }

    @Override
    @Transactional
    public void updateHospital( Hospital newHospital, Long id) {
        Hospital existHospital = hospitalRepository.getByHospitalId(id);
        existHospital.setName(newHospital.getName());
        existHospital.setAddress(newHospital.getAddress());
        existHospital.setImage(newHospital.getImage());
        hospitalRepository.saveHospital(existHospital);
    }

    @Override
    public void deleteByHospitalId(Long id) {
        hospitalRepository.deleteByHospitalId(id);


    }

    @Override
    public Hospital getByHospitalId(Long id) {
        return hospitalRepository.getByHospitalId(id);

    }

}
