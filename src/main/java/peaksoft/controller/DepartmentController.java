package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Department;
import peaksoft.models.Hospital;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    private HospitalService hospitalService;


    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{hospitalId}")
    public String getAllDepartment(Model model,@PathVariable("hospitalId")Long hospitalId){
        model.addAttribute("departments",departmentService.getAllDepartment(hospitalId));
        model.addAttribute("hospitals",hospitalService.getAllHospital());
        return "department/mainPage";
    }

    @GetMapping ("/{hospitalId}/new")
    public String create(Model model,@PathVariable("hospitalId")Long hospitalId){
        model.addAttribute("newDepartment", new Hospital());
        model.addAttribute("hospitals", hospitalId);
        return "department/saveDepartment";
    }


    @PostMapping("/{hospitalId}/save")
    public String save(@ModelAttribute("newDepartment") Department department,
                       @PathVariable("hospitalId")Long hospitalId){
        departmentService.saveDepartment(hospitalId,department);
        return "redirect:/departments/"+hospitalId;
    }


    @DeleteMapping("/{hospitalId}/{departmentId}/delete")
    public String deleteDepartment( @PathVariable("hospitalId") Long hospitalId,
                                    @ModelAttribute("department") Department department,
                                @PathVariable("departmentId") Long departmentId){
        departmentService.deleteByDepartmentId(departmentId);
        return "redirect:/departments/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{departmentId}/edit")
    public String edit(@PathVariable("hospitalId") Long id,Model model,
                       @PathVariable("departmentId") Long departmentId){
        model.addAttribute("department", departmentService.getByDepartmentId(departmentId));
        model.addAttribute("hospitalId",id);
        return "department/update";
    }

    @PostMapping("/{hospitalId}/{departmentId}/update")
    public String updateHospital(@PathVariable("hospitalId") Long hospitalId,
                                 @PathVariable("departmentId") Long departmentId,
                                 @ModelAttribute("department") Department newDepartment){
        departmentService.updateDepartment(newDepartment, departmentId);
        return "redirect:/departments/"+hospitalId;
    }
}
