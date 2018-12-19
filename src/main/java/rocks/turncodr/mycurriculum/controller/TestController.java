package rocks.turncodr.mycurriculum.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rocks.turncodr.mycurriculum.services.PdfGeneratorUtil;

@Controller
public class TestController {
    
    @Autowired
    PdfGeneratorUtil pdfGeneratorUtil;
    
	@RequestMapping(value = "/download" , method = RequestMethod.GET , produces = "application/pdf")
	public ResponseEntity<InputStreamResource> getTest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String,String> data = new HashMap<String,String>();
		data.put("name", "Chris");
		model.addAttribute("name", "Chris");
		return pdfGeneratorUtil.createPdfDownload("test", data, request, response);
	}
}
