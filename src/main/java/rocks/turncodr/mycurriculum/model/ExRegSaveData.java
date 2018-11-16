package rocks.turncodr.mycurriculum.model;

import java.util.List;

public class ExRegSaveData {

    /**
     * Date of expiration.
     */
    private ExReg exReg;

    private List<Module> newModuleStubs;

    private List<Module> modulesToBeMapped;

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
}
