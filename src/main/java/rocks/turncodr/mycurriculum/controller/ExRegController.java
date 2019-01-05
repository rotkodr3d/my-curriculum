package rocks.turncodr.mycurriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.model.Syllabus;
import rocks.turncodr.mycurriculum.model.Syllabus.Semester;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;
import rocks.turncodr.mycurriculum.services.PDFConfigManager;
import rocks.turncodr.mycurriculum.services.PdfGeneratorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for the Examination Regulations sites.
 */
@Controller
public class ExRegController {

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private ExRegJpaRepository exRegJpaRepository;
    
    @Autowired
    private PdfGeneratorUtil pdfGeneratorUtil;

    @Autowired
    private PDFConfigManager pdfConfigManager;
    
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
            Map<String,Object> exregData = getAndSetExregData(exregResult);
            for(Entry<String, ?> entry : exregData.entrySet())
                model.addAttribute(entry.getKey(),entry.getValue());
        } else {
            //the given id has no corresponding exreg --> display error message
            model.addAttribute("error", "exregSyllabus.exregDoesntExist");
        }
        return "exregSyllabus";
    }
    
    @RequestMapping(value = "/exreg/syllabus/pdf" , method = RequestMethod.GET) //produces="application/pdf"
    public ResponseEntity<?> getExRegHandbookPdf(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        Optional<ExReg> exregResult = exRegJpaRepository.findById(id);
        if (exregResult.isPresent()) {
            Map<String,Object> data = getAndSetExregData(exregResult);
            HashMap<String,String> textStyles = pdfConfigManager.getAllFonts();
            data.put("textStyles", textStyles);
            return pdfGeneratorUtil.createPdfDownload("exregSyllabusPDF", data, request, response);
        } else {
            //the given id has no corresponding exreg --> display error message
            return new ResponseEntity<String>("ERROR 404: THE REQUESTED RESOURCE DOESN'T EXIST!",HttpStatus.NOT_FOUND);
        }
    }
    /**
     * 
     * Returns a Map which contains all the data for an exreg.
     * 
     */
    private Map<String,Object> getAndSetExregData(Optional<ExReg> exregResult) {
        Map<String,Object> exregData= new HashMap<>(); 
        ExReg exreg = exregResult.get();

        //find modules for the given exreg
        List<Module> moduleList = moduleJpaRepository.findByExReg(exreg);
        exregData.put("exreg", exreg);
        //model.addAttribute("exreg", exreg);

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
        exregData.put("syllabus", syllabus);
        return exregData;
    }
}
