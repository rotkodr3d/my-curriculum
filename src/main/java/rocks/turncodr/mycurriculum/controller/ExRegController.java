package rocks.turncodr.mycurriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rocks.turncodr.mycurriculum.model.AreaOfStudies;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.AreaOfStudiesJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Controller for the Examination Regulations sites.
 */
@Controller
public class ExRegController {

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private AreaOfStudiesJpaRepository areaOfStudiesJpaRepository;

    @GetMapping("/exreg/create")
    public String getExRegCreate(Model model) {
        // fetching not mapped modules in a moduleList by selecting only modules, where exReg-attribute = null
        List<Module> moduleList = moduleJpaRepository.findByExReg(null);
        List<AreaOfStudies> areaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        HashMap<Integer, AreaOfStudies> areaOfStudiesMap = new HashMap<>();
        model.addAttribute("moduleList", moduleList);
        for (AreaOfStudies areaOfStudies : areaOfStudiesList) {
            areaOfStudiesMap.put(areaOfStudies.getId(), areaOfStudies);
        }
        model.addAttribute("areaOfStudiesMap", areaOfStudiesMap);
        return "exregCreate";
    }
}
