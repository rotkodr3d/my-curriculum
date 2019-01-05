package rocks.turncodr.mycurriculum.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfGeneratorUtil {
    @Autowired
    private TemplateEngine templateEngine;
      
    public ResponseEntity<InputStreamResource> createPdfDownload(String templateName, Map<?,?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
        if (map != null) {
            for (Entry<?,?> entry : map.entrySet()) {
                context.setVariable(entry.getKey().toString(), entry.getValue());
            } 
        }
        
          String processedHtml = templateEngine.process(templateName, context);
          System.out.println(processedHtml);
          ByteArrayOutputStream os = null;
            try {
                os = new ByteArrayOutputStream();

                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(processedHtml);
                renderer.layout();
                renderer.createPDF(os, false);
                renderer.finishPDF();
                
                System.out.println("PDF created successfully, returning...");
                return ResponseEntity.ok()
                		.contentLength(os.size())
                		.contentType(MediaType.parseMediaType("application/pdf"))
                		.body(new InputStreamResource(new ByteArrayInputStream(os.toByteArray())));
            }
            finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) { /*ignore*/ }
                }
            }
    }
}
