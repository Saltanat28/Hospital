package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Appointment;
import peaksoft.service.*;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {


    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;



    @GetMapping("/{hospitalId}")
    public String getALL(@PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("appointments",appointmentService.getAllByHospitalId(hospitalId));
        return "appointment/mainPage";
    }

    @GetMapping("/{hospitalId}/new")
    public String newApp(@PathVariable("hospitalId") Long hospitalId,
                         Model model){
            model.addAttribute("hosId", hospitalId);
            model.addAttribute("appointment", new Appointment());
            model.addAttribute("patients", patientService.getAllPatients(hospitalId));
            model.addAttribute("doctors", doctorService.getAllDoctors(hospitalId));
            model.addAttribute("departments", departmentService.getAllDepartment(hospitalId));
        return "appointment/new";
    }

    @PostMapping("/{hospitalId}/save")
    public String save(@PathVariable("hospitalId") Long hospitalId,
                       @ModelAttribute("appointment") Appointment appointment){


        appointmentService.save(hospitalId, appointment);
        return "redirect:/appointments/{hospitalId}";
    }

    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String deleteAppointment(@PathVariable("appointmentId") Long appointmentId,
                                    @PathVariable Long hospitalId){

        appointmentService.deleteAppointment(appointmentId);
        return "redirect:/appointments/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{appointmentId}/edit")
    public String edit(Model model, @PathVariable("hospitalId") Long hospitalId,
                                    @PathVariable("appointmentId") Long appointmentId){

        model.addAttribute("appointment", appointmentService.getByAppointmentId(appointmentId));
        model.addAttribute("id", hospitalId);

        model.addAttribute("patients", patientService.getAllPatients(hospitalId));
        model.addAttribute("doctors", doctorService.getAllDoctors(hospitalId));
        model.addAttribute("departments", departmentService.getAllDepartment(hospitalId));
        return "appointment/update";
    }

    @PostMapping("/{hospitalId}/{appointmentId}/update")
    public String update( @PathVariable("hospitalId") Long hospitalId,
                          @PathVariable("appointmentId") Long appointmentId,
                          @ModelAttribute("appointment") Appointment newAppointment){
        appointmentService.updateAppointment(newAppointment, appointmentId);
        return "redirect:/appointments/"+hospitalId;

    }





}
