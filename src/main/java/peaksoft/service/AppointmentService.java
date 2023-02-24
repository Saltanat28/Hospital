package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.models.Appointment;
import peaksoft.models.Hospital;

import java.util.List;
@Service
public interface AppointmentService {
    List<Appointment> getAllByHospitalId(Long hospitalId);

    void save(Long hospitalId, Appointment appointment);

    void deleteAppointment( Long appointmentId);

    Appointment getByAppointmentId(Long appointmetnId);

    void updateAppointment(Appointment newAppointment, Long appointmentId);

}
