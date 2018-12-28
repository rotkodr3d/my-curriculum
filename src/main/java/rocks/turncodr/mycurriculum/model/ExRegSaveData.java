package rocks.turncodr.mycurriculum.model;

import java.util.List;

/**
 * Data Object for the incoming JSON for /exreg/save.
 */
public class ExRegSaveData {

    /**
     * Date of expiration.
     */
    private ExReg exReg;

    private List<Module> newModuleStubs;

    private List<Module> modulesToBeMapped;

    private AreaOfStudies areaOfStudies;

    public ExReg getExReg() {
        return exReg;
    }

    public void setExReg(ExReg exReg) {
        this.exReg = exReg;
    }

    public List<Module> getNewModuleStubs() {
        return newModuleStubs;
    }

    public void setNewModuleStubs(List<Module> newModuleStubs) {
        this.newModuleStubs = newModuleStubs;
    }

    public List<Module> getModulesToBeMapped() {
        return modulesToBeMapped;
    }

    public void setModulesToBeMapped(List<Module> modulesToBeMapped) {
        this.modulesToBeMapped = modulesToBeMapped;
    }

    public AreaOfStudies getAreaOfStudies() {
        return areaOfStudies;
    }

    public void setAreaOfStudies(AreaOfStudies areaOfStudies) {
        this.areaOfStudies = areaOfStudies;
    }
}
