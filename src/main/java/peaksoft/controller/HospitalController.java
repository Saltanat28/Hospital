package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Hospital;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping()
    public String getAllHospital(Model model){
        model.addAttribute("hospitals", hospitalService.getAllHospital());

        return "hospital/hospitals";

    }

    @GetMapping ("/new")
    public String create(Model model){
        model.addAttribute("hospital", new Hospital());
        return "hospital/saveHospital";
    }


    @PostMapping("/savePage")
    public String save(@ModelAttribute("hospital") Hospital hospital){
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }


    @DeleteMapping("/{id}/delete")
    public String deleteHospital(@PathVariable("id") Long hospitalId){
        hospitalService.deleteByHospitalId(hospitalId);
        return "redirect:/hospitals";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,Model model){
        model.addAttribute("hospital",hospitalService.getByHospitalId(id));
        return "hospital/update";
    }

    @PutMapping("/{id}/update")
    public String updateCompany(@PathVariable("id") Long id,@ModelAttribute("hospital") Hospital newHospital){
        hospitalService.updateHospital(newHospital, id);
        return "redirect:/hospitals";
    }

    @GetMapping("/details/{hospitalId}")
    public String details(@PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("hospital",hospitalService.getByHospitalId(hospitalId));
        return "hospital/details";
    }
}
