package rocks.turncodr.mycurriculum.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rocks.turncodr.mycurriculum.services.PdfGeneratorUtil;

@Controller
public class TestController {
    
    @Autowired
    PdfGeneratorUtil pdfGeneratorUtil;
    
	@GetMapping("/test")
	public String getTest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String,String> data = new HashMap<String,String>();
		data.put("name", "Chris");
		model.addAttribute("name", "Chris");
		pdfGeneratorUtil.createPdf("test", data, request, response);
		return "test";
	}
}
