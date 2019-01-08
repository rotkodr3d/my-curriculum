package rocks.turncodr.mycurriculum.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Thymeleaf configuration for the syllabus pdf.
 */
@Configuration
public class ThymeleafConfiguration {

    @Bean
    public ClassLoaderTemplateResolver exregPdfResolver() {
        ClassLoaderTemplateResolver exregPdfResolver = new ClassLoaderTemplateResolver();
        exregPdfResolver.setPrefix("templates/");
        exregPdfResolver.setTemplateMode("HTML5");
        exregPdfResolver.setSuffix(".html");
        //flying saucer requires xhtml
        exregPdfResolver.setTemplateMode("XHTML");
        exregPdfResolver.setCharacterEncoding("UTF-8");
        exregPdfResolver.setOrder(1);
        return exregPdfResolver;
    }
}
