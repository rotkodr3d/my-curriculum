package rocks.turncodr.mycurriculum.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PDFConfigManager {

    private final String configName = "pdfsytle.properties";
    private Properties properties;
    
    public PDFConfigManager() {
        File file = new File(configName);
        if(!file.exists()) {
            createStandardConfigFile(file); 
        } else {
            loadConfigFile(file);
        }
    }
    
    private void createStandardConfigFile(File file) {
        try {
            properties = new Properties();
            properties.setProperty("headingsFont", "Arial");
            properties.setProperty("headingsColor", "black");
            properties.setProperty("textFont", "Arial");
            properties.setProperty("textColor", "black");
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, null);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    private void loadConfigFile(File file) {
        properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @return All fonts saved in the config file.
     */
    public HashMap<String,String> getAllFonts() {
        HashMap<String,String> propertiesMap = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            propertiesMap.put(name, properties.getProperty(name));
        }
        return propertiesMap;
    }
}
