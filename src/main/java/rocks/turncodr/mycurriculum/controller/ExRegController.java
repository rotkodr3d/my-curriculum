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
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

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

}
