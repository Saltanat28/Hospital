package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Doctor;
import peaksoft.models.Hospital;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;
    private final DoctorService doctorService;


    @GetMapping("/{hospitalId}")
    public String getAllDoctors(Model model, @PathVariable("hospitalId") Long hospitalId){
        model.addAttribute("doctors", doctorService.getAllDoctors(hospitalId));
        return "doctor/mainPage";
    }

    @GetMapping ("/{hospitalId}/new")
    public String create( @PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("doctors", new Doctor());
        model.addAttribute("hospitals",hospitalService.getAllHospital());
        model.addAttribute("departments", departmentService.getAllDepartment(hospitalId));
        return "doctor/saveDoctor";
    }


    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable("hospitalId")Long hospitalId,@ModelAttribute("doctors") Doctor doctor){
        doctorService.saveDoctors(hospitalId,doctor);
        return "redirect:/doctors/"+hospitalId;
    }


    @DeleteMapping("/{hospitalId}/{id}/delete")
    public String deleteDoctor(@PathVariable("id") Long id,
                               @PathVariable("hospitalId") Long hospitalId){
        doctorService.deleteByDoctorId(id);
        return "redirect:/doctors/"+hospitalId;
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,Model model){
        Doctor doctorById = doctorService.getByDoctorId(id);
        model.addAttribute("doctor", doctorById);
        model.addAttribute("hospitalId", doctorById.getHospital().getId());
        return "doctor/update";
    }

    @PostMapping("/{id}/{doctorId}/update")
    public String updateDoctor(@PathVariable("id") Long id,
                               @PathVariable("doctorId") Long doctorId,
                               @ModelAttribute("doctor") Doctor newDoctor){
        doctorService.updateDoctor(newDoctor, doctorId);
        return "redirect:/doctors/"+id;
    }
}
