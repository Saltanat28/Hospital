package peaksoft.repository;

import peaksoft.models.Appointment;

import java.util.List;

public interface AppointmentRepository {
    List<Appointment> getAllByHospitalId(Long hospitalId);

    void save(Long hospitalId, Appointment appointment);

    void deleteAppointment( Long appointmentId);

    Appointment getByAppointmentId(Long appointmentId);

    void save(Appointment appointment);
}
