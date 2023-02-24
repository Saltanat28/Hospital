package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Appointment;
import peaksoft.models.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Appointment> getAllByHospitalId(Long hospitalId) {
        return entityManager.createQuery("select a from  Hospital h join h.appointments a where h.id=:hosId", Appointment.class)
                .setParameter("hosId", hospitalId).getResultList();
    }

    @Override
    public void save(Long hospitalId, Appointment appointment) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.getAppointments().add(appointment);
        entityManager.persist(appointment);
    }

    @Override
    public void deleteAppointment( Long appointmentId) {
        Appointment appointment = entityManager.find(Appointment.class,appointmentId);
        entityManager.remove(appointment);
    }

    @Override
    public Appointment getByAppointmentId(Long appointmentId) {
        return entityManager.find(Appointment.class,appointmentId);
    }

    @Override
    public void save(Appointment appointment) {
        entityManager.persist(appointment);
    }


}
