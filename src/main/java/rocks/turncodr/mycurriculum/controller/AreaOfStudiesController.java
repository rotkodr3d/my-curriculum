package rocks.turncodr.mycurriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/areaofstudies/list")
    public String getAreaOfStudiesList(Model model) {
        List<AreaOfStudies> areaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        model.addAttribute("areaOfStudies", areaOfStudiesList);
        return "areaOfStudiesList";
    }

    @GetMapping("/areaofstudies/create")
    public String getAreaOfStudiesCreate(Model model) {
        List<AreaOfStudies> existingAreaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        model.addAttribute("areaOfStudies", new AreaOfStudies());
        model.addAttribute("existingAreaOfStudiesList", existingAreaOfStudiesList);
        return "areaOfStudiesCreate";
    }

    @GetMapping("/areaofstudies/edit")
    public String getAreaOfStudiesEdit(@RequestParam(value = "id", required = true, defaultValue = "0") String urlId, Model model) {
        Integer id;
        try {
            id = Integer.parseInt(urlId);
        } catch (NumberFormatException e) {
            id = 0;
        }
        Optional<AreaOfStudies> aosResult = areaOfStudiesJpaRepository.findById(id);
        if (aosResult.isPresent()) {
            AreaOfStudies aos = aosResult.get();
            model.addAttribute("areaOfStudies", aos);
            model.addAttribute("editMode", true);
            model.addAttribute("moduleColor", '#' + String.format("%06X" ,aos.getColor()));
        } else {
            model.addAttribute("error", "areaOfStudiesEdit.doesntExist");
        }
        return "areaOfStudiesCreate";
    }

    @PostMapping("/areaofstudies/create")
    public String postAreaOfStudiesCreate(@Valid @ModelAttribute AreaOfStudies areaOfStudies,
            BindingResult bindingResult) {
        areaOfStudies.setName(areaOfStudies.getName());
        colorValidator.validate(areaOfStudies, bindingResult);
        if (bindingResult.hasErrors()) {
            // validation failed stay on page
            return "areaOfStudiesCreate";
        }
        // validation has been successful save areaOfStudies
        areaOfStudies.setColorRGB(intToRGB(areaOfStudies.getColor()));
        areaOfStudiesJpaRepository.save(areaOfStudies);
        return "redirect:/areaofstudies/list";
    }

    @PostMapping("/areaofstudies/edit")
    public String postAreaOfStudiesEdit(@RequestParam(value = "id", required = false, defaultValue = "0") String urlId, @Valid @ModelAttribute AreaOfStudies areaOfStudies,
            Model model, BindingResult bindingResult) {
        Integer id;
        try {
            id = Integer.parseInt(urlId);
        } catch (NumberFormatException e) {
            id = 0;
        }
        areaOfStudies.setName(areaOfStudies.getName());
        colorValidator.setEditValidation(true);
        colorValidator.validate(areaOfStudies, bindingResult);
        colorValidator.setEditValidation(false);
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", true);
            // validation failed stay on page
            return "areaOfStudiesCreate";
        }
        // validation has been successful save areaOfStudies
        areaOfStudies.setColorRGB(intToRGB(areaOfStudies.getColor()));
        areaOfStudiesJpaRepository.save(areaOfStudies);
        return "redirect:/areaofstudies/list";
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
