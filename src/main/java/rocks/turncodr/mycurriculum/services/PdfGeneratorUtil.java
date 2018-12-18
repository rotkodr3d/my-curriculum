package rocks.turncodr.mycurriculum.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfGeneratorUtil {
    @Autowired
    private TemplateEngine templateEngine;
    public void createPdf(String templateName, Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
        if (map != null) {
             Iterator itMap = map.entrySet().iterator();
               while (itMap.hasNext()) {
              Map.Entry pair = (Map.Entry) itMap.next();
                  context.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }
        
          String processedHtml = templateEngine.process(templateName, context);
          FileOutputStream os = null;
          String fileName = UUID.randomUUID().toString();
            try {
                final File outputFile = File.createTempFile(fileName, ".pdf");
                os = new FileOutputStream(outputFile);

                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(processedHtml);
                renderer.layout();
                renderer.createPDF(os, false);
                renderer.finishPDF();
                System.out.println("PDF created successfully");
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
