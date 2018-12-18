package rocks.turncodr.mycurriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.model.Syllabus;
import rocks.turncodr.mycurriculum.model.Syllabus.Semester;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for the Examination Regulations sites.
 */
@Controller
public class ExRegController {

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private ExRegJpaRepository exRegJpaRepository;

    @GetMapping("/exreg/create")
    public String getExRegCreate(Model model) {
        // fetching not mapped modules in a moduleList by selecting only modules, where
        // exReg-attribute = null
        List<Module> moduleList = moduleJpaRepository.findByExReg(null);
        model.addAttribute("moduleList", moduleList);

        return "exregCreate";
    }

    @GetMapping("/exreg/syllabus")
    public String getExRegHandbook(@RequestParam(value = "id", required = false, defaultValue = "0") String urlId, Model model) {
        Integer id;
        try {
            id = Integer.parseInt(urlId);
        } catch (NumberFormatException e) {
            id = 0;
        }
        //find exreg for the given id
        Optional<ExReg> exregResult = exRegJpaRepository.findById(id);
        if (exregResult.isPresent()) {
            ExReg exreg = exregResult.get();

            //find modules for the given exreg
            List<Module> moduleList = moduleJpaRepository.findByExReg(exreg);
            model.addAttribute("exreg", exreg);

            Map<Integer, List<Module>> semesterMap = new HashMap<>();
            for (Module module : moduleList) {
                int semester = module.getSemester();
                List<Module> modules = semesterMap.get(semester);

                if (modules == null) {
                    modules = new ArrayList<>();
                    semesterMap.put(semester, modules);
                }
                modules.add(module);
            }
            List<Integer> keys = new ArrayList<>(semesterMap.keySet());
            Collections.sort(keys);
            Syllabus syllabus = new Syllabus();

            for (Integer key : keys) {
                List<Module> modules = semesterMap.get(key);
                Collections.sort(modules, Module.ALPHABETICAL_ORDER);
                Semester semester = new Semester(modules, key);
                syllabus.getSemesters().add(semester);
            }
            model.addAttribute("syllabus", syllabus);
        } else {
            //the given id has no corresponding exreg --> display error message
            model.addAttribute("error", "exregSyllabus.exregDoesntExist");

        }
        return "exregSyllabus";

    }

}
