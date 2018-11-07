package rocks.turncodr.mycurriculum.model;

import java.util.List;

/**
 * Command Object Wrapper for ExReg. Required for /exReg/create and /exReg/edit to map the Modules to the ExReg.
 */
public class ExRegWrapper {

    /**
     * List of unmapped Modules that are eligible to be mapped to the current ExReg.
     */
    private List<Module> modules;
    /**
     * List of Modules that have been selected to be mapped to the current ExReg.
     */
    private List<Module> selectedModules;
    /**
     * Examination Regulations Object that is edited or created.
     */
    private ExReg exReg;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Module> getSelectedModules() {
        return selectedModules;
    }

    public void setSelectedModules(List<Module> selectedModules) {
        this.selectedModules = selectedModules;
    }

    public ExReg getExReg() {
        return exReg;
    }

    public void setExReg(ExReg exReg) {
        this.exReg = exReg;
    }
}
