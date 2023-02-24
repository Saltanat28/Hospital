package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")

public class PatientController {
    private final PatientService patientService;
    private final HospitalService hospitalService;

    @Autowired
    public PatientController(PatientService patientService, HospitalService hospitalService) {
        this.patientService = patientService;
        this.hospitalService = hospitalService;
    }


    @GetMapping("/{hospitalId}")
    public String getAllPatients(Model model, @PathVariable("hospitalId") Long hospitalId) {
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("patients", patientService.getAllPatients(hospitalId));
        return "patient/mainPage";
    }


    @GetMapping("/{hospitalId}/new")
    public String create(Model model, @PathVariable("hospitalId") Long hospitalId) {
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("hospitals", hospitalId);
        return "patient/savePatient";
    }

    @PostMapping("/{hospitalId}/save")
    public String save(@ModelAttribute("patients") Patient patient, @PathVariable("hospitalId") Long hospitalId) {
        patientService.savePatient(hospitalId, patient);
        return "redirect:/patients/" + hospitalId;
    }


    @DeleteMapping("/{hospitalId}/{id}/delete")
    public String deletePatient(@PathVariable("id") Long id,
                                @PathVariable("hospitalId") Long hospitalId,
                                @ModelAttribute("patient") Patient patient) {
        patientService.deletePatient(id);
        return "redirect:/patients/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id,
                       @PathVariable("hospitalId") Long hospitalId) {
        model.addAttribute("patient", patientService.getByPatientId(id));
        model.addAttribute("hospitalId", hospitalId);
        return "patient/update";
    }

    @PostMapping("/{hospitalId}/{id}/update")
    public String updatePatient(@PathVariable("id") Long id,
                                @PathVariable("hospitalId") Long hospitalId,
                                @ModelAttribute("patient") Patient newPatient) {
        patientService.updatePatient(newPatient, id);
        return "redirect:/patients/"+hospitalId;
    }
}
