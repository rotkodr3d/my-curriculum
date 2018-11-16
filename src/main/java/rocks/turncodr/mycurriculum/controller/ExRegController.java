package rocks.turncodr.mycurriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.ExRegSaveData;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        //add ExReg as Command Object for the form values
        model.addAttribute("exReg", new ExReg());

        return "exregCreate";
    }

    @PostMapping("/exreg/create")
    public String postExRegCreate(@ModelAttribute ExReg exReg, Model model) {

        //save the newly created ExReg
        ExReg savedExReg = exRegJpaRepository.save(exReg);

        return "redirect:/exreg/list";
    }

    @GetMapping("/test/json")
    public String getTestJson() {
        return "test";
    }

    @PostMapping(value = "/test/json", consumes = "application/json")
    public ResponseEntity<Module> testJson(@RequestBody ExReg json) {
        Module result = new Module();
        result.setCode(json.getName());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/exreg/mapmodules/getmodules", consumes = "application/json")
    public ResponseEntity<List<Module>> postExRegMapModulesGetModules() {
        List<Module> result = moduleJpaRepository.findByExReg(null);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/exreg/save", consumes = "application/json")
    public ResponseEntity<String> postExRegSave(@RequestBody ExRegSaveData data) {
        ExReg exReg = exRegJpaRepository.save(data.getExReg());

        for (Module module : data.getNewModuleStubs()) {
            module.setExReg(exReg);
        }
        moduleJpaRepository.saveAll(data.getNewModuleStubs());

        List<Module> modulesToBeMapped = new ArrayList<>();
        for (Module module : data.getModulesToBeMapped()) {
            Optional possibleModule = moduleJpaRepository.findById(module.getId());
            if (possibleModule.isPresent()) {
                Module dbModule = (Module) possibleModule.get();
                dbModule.setSemester(module.getSemester());
                dbModule.setPositionInSemester(module.getPositionInSemester());
                modulesToBeMapped.add(dbModule);
            } else {
                //Warn the user, that the module he's trying to map does not exist any more.
            }
        }
        moduleJpaRepository.saveAll(modulesToBeMapped);

        return new ResponseEntity<>("data saved to Database", HttpStatus.OK);
    }

}
