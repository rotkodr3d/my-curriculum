package rocks.turncodr.mycurriculum.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.thymeleaf.standard.expression.OGNLContextPropertyAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

@Controller
public class TestController {

	@GetMapping("/test/pdf")
	public String getTest() throws IOException, DocumentException {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");

		TemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("name", "Chris");

		// Get the plain HTML with the resolved ${name} variable!
		String html = templateEngine.process("test", context);

		OutputStream outputStream = new FileOutputStream("message.pdf");
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(outputStream);
		outputStream.close();
		return "test";

	}
}
