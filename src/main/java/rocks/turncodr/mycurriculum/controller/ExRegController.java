package rocks.turncodr.mycurriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.ExRegWrapper;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import java.util.List;

/**
 * Controller for the Examination Regulations sites.
 */
@Controller
public class ExRegController {

    @Autowired
    private ExRegJpaRepository exRegJpaRepository;
    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @GetMapping("/exreg/create")
    public String getExRegCreate(Model model) {

        //find modules that aren't mapped (ExReg == null)
        List<Module> modules = moduleJpaRepository.findByExReg(null);

        //add ExRegWrapper as Command Object for the form values
        ExRegWrapper exRegWrapper = new ExRegWrapper();
        exRegWrapper.setModules(modules);
        exRegWrapper.setExReg(new ExReg());
        model.addAttribute("exRegWrapper", exRegWrapper);

        return "exregCreate";
    }

    @PostMapping("/exreg/create")
    public String postExRegCreate(@ModelAttribute ExRegWrapper exRegWrapper, Model model) {

        //save the newly created ExReg
        ExReg savedExReg = exRegJpaRepository.save(exRegWrapper.getExReg());

        //map the Modules that have been selected to the ExReg
        List<Module> selectedModules = exRegWrapper.getSelectedModules();
        for (Module selectedModule : selectedModules) {
            selectedModule.setExReg(savedExReg);
        }

        //update the Module entities
        moduleJpaRepository.saveAll(selectedModules);

        return "redirect:/exreg/showList";
    }

}
