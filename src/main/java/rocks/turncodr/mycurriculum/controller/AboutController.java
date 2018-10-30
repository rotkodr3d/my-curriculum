package rocks.turncodr.mycurriculum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rocks.turncodr.mycurriculum.model.Curriculum;
import rocks.turncodr.mycurriculum.services.CurriculumJpaRepository;

/**
 * Controller class directing to the about page of the application.
 *
 */
@Controller
public class AboutController {
    @Autowired
    private CurriculumJpaRepository curriculumService;

    @GetMapping("/about")
    public String about(Model model) {
        List<Curriculum> curriculum = curriculumService.findAll();
        model.addAttribute("curriculum", curriculum);
        return "about";
    }
}
