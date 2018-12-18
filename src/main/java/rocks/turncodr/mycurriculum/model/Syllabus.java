package rocks.turncodr.mycurriculum.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a syllabus. It holds the data which creates the syllabus html and pdf.
 */
public class Syllabus {

    private List<Semester> semesters;


    public Syllabus() {
        semesters = new ArrayList<>();
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    /**
     * @return sum of all module credits in this syllabus
     */
    public int getTotalCredits() {
        int totalCredits = 0;
        for (Semester semester : semesters) {
            totalCredits += semester.getCredits();
        }
        return totalCredits;
    }

    /**
     * Holds semester data.
     */
    public static class Semester {
        /**
         * List of modules that are schedules for this semester.
         */
        private List<Module> modules;
        /**
         * Number of the semester, e.g. Semester 1 is the first semester of the syllabus.
         */
        private int number;

        public Semester(List<Module> modules, int number) {
            super();
            this.modules = modules;
            this.number = number;
        }

        public List<Module> getModules() {
            return modules;
        }

        public void setModules(List<Module> modules) {
            this.modules = modules;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        /**
         * @return sum of all module credits scheduled for this semester
         */
        public int getCredits() {
            int semesterCredits = 0;
            for (Module module : modules) {
                semesterCredits += module.getCredits();
            }
            return semesterCredits;
        }
    }

}
