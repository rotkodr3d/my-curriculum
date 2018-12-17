package rocks.turncodr.mycurriculum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rocks.turncodr.mycurriculum.model.Curriculum;
import rocks.turncodr.mycurriculum.model.ExReg;
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
}
