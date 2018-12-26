package rocks.turncodr.mycurriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import rocks.turncodr.mycurriculum.model.AreaOfStudies;
import rocks.turncodr.mycurriculum.services.AreaOfStudiesJpaRepository;
import rocks.turncodr.mycurriculum.validation.AreaOfStudiesValidator;
/**
*
* Controller for area of studies.
*
*/
@Controller
public class AreaOfStudiesController {

    @Autowired
    AreaOfStudiesJpaRepository areaOfStudiesJpaRepository;

    @Autowired
    AreaOfStudiesValidator colorValidator;

    @GetMapping("/areaOfStudies/list")
    public String getAreaOfStudiesList(Model model) {
        List<AreaOfStudies> areaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        model.addAttribute("areaOfStudies", areaOfStudiesList);
        return "areaOfStudiesList";
    }

    @GetMapping("/areaOfStudies/create")
    public String getAreaOfStudiesCreate(Model model) {
        List<AreaOfStudies> existingAreaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        model.addAttribute("areaOfStudies", new AreaOfStudies());
        model.addAttribute("existingAreaOfStudiesList", existingAreaOfStudiesList);
        return "areaOfStudiesCreate";
    }

    @PostMapping("/areaOfStudies/create")
    public String postAreaOfStudiesCreate(@Valid @ModelAttribute AreaOfStudies areaOfStudies,
            BindingResult bindingResult) {
        // remove leading/trailing white spaces before validation
        areaOfStudies.setName(areaOfStudies.getName());
        colorValidator.validate(areaOfStudies, bindingResult);
        if (bindingResult.hasErrors()) {
            // validation failed stay on page
            return "areaOfStudiesCreate";
        }
        // validation has been successful save areaOfStudies
        areaOfStudies.setColorRGB(intToRGB(areaOfStudies.getColor()));
        areaOfStudiesJpaRepository.save(areaOfStudies);
        return "redirect:/areaOfStudies/list";
    }
    
    /**
     * Translates the color back to RGB representation and returns it as a string.
     *
     * @param colorAsInt The color as integer
     * @return r = red component, g = green component, b = blue component
     */
    @SuppressWarnings("checkstyle:magicnumber")
    private String intToRGB(int colorAsInt) {
        colorAsInt >>>= 0;
        int b = colorAsInt & 0xFF, g = (colorAsInt & 0xFF00) >>> 8, r = (colorAsInt & 0xFF0000) >>> 16;

        return r + "," + g + "," + b;
    }
}
