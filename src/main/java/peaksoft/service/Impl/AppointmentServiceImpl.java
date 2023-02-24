package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Appointment;
import peaksoft.models.Doctor;
import peaksoft.models.Hospital;
import peaksoft.models.Patient;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.util.List;
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DepartmentRepository departmentRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Appointment> getAllByHospitalId(Long hospitalId) {
        return appointmentRepository.getAllByHospitalId(hospitalId);
    }

    @Override
    public void save(Long hospitalId, Appointment appointment) {
        appointment.setPatient(patientRepository.getByPatientId(appointment.getPatientId()));
        appointment.setDoctor(doctorRepository.getByDoctorId(appointment.getDoctorId()));
        appointment.setDepartment(departmentRepository.getByDepartmentId(appointment.getDepartmentId()));
        appointmentRepository.save(hospitalId, appointment);
    }

    @Override
    public void deleteAppointment( Long appointmentId) {

        for (Hospital hospital : hospitalRepository.getAllHospital()) {
           hospital.getAppointments().remove(getByAppointmentId(appointmentId));
        }
        for (Patient patient : patientRepository.findAllPatient()) {
            patient.getAppoitmentList().remove(getByAppointmentId(appointmentId));
        }
        for (Doctor allDoctor : doctorRepository.findAllDoctors()) {
            allDoctor.getAppointmentList().remove(getByAppointmentId(appointmentId));
        }

        appointmentRepository.deleteAppointment(appointmentId);
    }

    @Override
    public Appointment getByAppointmentId(Long appointmentId) {
        return appointmentRepository.getByAppointmentId(appointmentId);
    }

    @Override
    public void updateAppointment(Appointment newAppointment, Long appointmentId) {
        Appointment appointment = getByAppointmentId(appointmentId);
        appointment.setId(newAppointment.getId());
        appointment.setDate(newAppointment.getDate());
        appointment.setDepartment(departmentRepository.getByDepartmentId(newAppointment.getDepartmentId()));
        appointment.setDoctor(doctorRepository.getByDoctorId(newAppointment.getDoctorId()));
        appointment.setPatient(patientRepository.getByPatientId(newAppointment.getPatientId()));
        appointmentRepository.save(appointment);
    }


}
