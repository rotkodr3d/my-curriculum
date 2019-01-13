package rocks.turncodr.mycurriculum.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

/**
 * Exreg modules overview controller.
 *
 */
@Controller
public class ModulesOverviewController {
    @Autowired
    private ExRegJpaRepository exRegJpaRepository;
    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @GetMapping("/exreg/overview")
    public String getModulesOverview(@RequestParam(value = "id", required = false, defaultValue = "0") String urlId,
            Model model) {
        Integer id = Integer.parseInt(urlId);
        Optional<ExReg> exregResult = exRegJpaRepository.findById(id);
        if (exRegJpaRepository.existsById(id)) {
            ExReg exreg = exregResult.get();
            model.addAttribute("exRegSemester", getNumberOfSemesters(exreg));
            model.addAttribute("exRegModules", moduleJpaRepository.findByExReg(exreg));
            model.addAttribute("exreg", exreg);
            return "modulesOverview";
        } else {
            model.addAttribute("error", "exregSyllabus.exregDoesntExist");
            return "modulesOverview";
        }
    }

    @RequestMapping("/details/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String getMouleDetails(@PathVariable("id") String id, Model model) {
        Module module = moduleJpaRepository.getOne(Integer.parseInt(id));
        model.addAttribute("module", module);
        return "/fragments/popupModuleOverview :: showModuleDetails";
    }

    private int getNumberOfSemesters(ExReg exreg) {
        int semester = 0;
        for (Module module : moduleJpaRepository.findByExReg(exreg)) {
            int moduleSemester = module.getSemester();
            semester = (moduleSemester > semester) ? moduleSemester : semester;
        }
        return semester;
    }
}
