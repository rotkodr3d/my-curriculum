package rocks.turncodr.mycurriculum.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import rocks.turncodr.mycurriculum.model.AreaOfStudies;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.AreaOfStudiesJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import javax.validation.Valid;

/**
 * Controller class for the module part of the application.
 */
@Controller
public class ModuleController {

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private AreaOfStudiesJpaRepository areaOfStudiesJpaRepository;

    @GetMapping("/module/create")
    public String getModuleCreate(Model model) {
        // Create empty module object for form data (will be received in
        // postModuleCreate as parameter 'module')
        Module module = new Module();
        model.addAttribute("module", module);
        List<AreaOfStudies> areaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        model.addAttribute("areaOfStudies", areaOfStudiesList);
        // Set moduleCreate.html as template to be parsed
        return "moduleCreate";
    }

    @PostMapping("/module/create")
    public String postModuleCreate(@Valid @ModelAttribute Module module, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            // validation failed, therefore stay on the page
            return "moduleCreate";

        } else {

            // Saving the form values to database
            moduleJpaRepository.save(module);

            return "redirect:/module/list";
        }
    }

    @GetMapping("/module/list")
    public String getModuleShowList(Model model) {
        // Fetching all modules from database
        List<Module> moduleList = moduleJpaRepository.findAll();
        model.addAttribute("moduleList", moduleList);

        // Set moduleShowList.html as template to be parsed
        return "moduleList";
    }

}
