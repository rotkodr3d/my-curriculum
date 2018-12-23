package rocks.turncodr.mycurriculum.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

@Controller
public class ModulesOverviewController {
    @Autowired
    private ExRegJpaRepository exRegJpaRepository;
    @Autowired
    private ModuleJpaRepository moduleJpaRepository;
    
    @GetMapping("/modules/overview")
    public String createCurriculumForm(Model model) {
        List<ExReg> exregs = exRegJpaRepository.findAll();
        ExReg exreg = exregs.get(0);
        model.addAttribute("exRegSemester", exreg.getNumberOfSemesters());
        model.addAttribute("exRegModules", moduleJpaRepository.findByExReg(exreg));
        return "modulesOverview";
    }
    
    @RequestMapping("/details/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String getMouleDetails(@PathVariable("id") String id, Model model) {
        Module module = moduleJpaRepository.getOne(Integer.parseInt(id));
        model.addAttribute("module", module);
        return "/fragments/popupModuleOverview :: showModuleDetails";
    }
}
