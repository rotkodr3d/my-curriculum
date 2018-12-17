package rocks.turncodr.mycurriculum.application.lifecycle;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;
import rocks.turncodr.mycurriculum.model.Curriculum;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.services.CurriculumJpaRepository;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;

/**
 * Lifecycle bean that creates demo curricula.
 *
 *
 */
@Component
public class DemoCurriculumLifecycleBean implements SmartLifecycle {

    private boolean running;

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private CurriculumJpaRepository curriculumService;
    
    @Autowired
    private ExRegJpaRepository exregJpaRepository;
    @Override
    public void start() {
        running = true;
        this.createExreg();
        this.createModules();
        this.createCurriculum();
    }
    
    @SuppressWarnings("checkstyle:magicnumber")
    private void createExreg() {
        ExReg wib = new ExReg();
        wib.setId(1);
        wib.setName("Wirtschaftsinformatik");
        wib.setExpiresOn(new Date(1538344800000l));
        wib.setNumberOfSemesters(6);
        exregJpaRepository.save(wib);
    }
    
    @SuppressWarnings("checkstyle:magicnumber")
    private void createCurriculum() {
        List<Curriculum> curriculum = curriculumService.findAll();
        if (curriculum.size() == 0) {
            Curriculum wib = new Curriculum();
            wib.setName("Wirtschaftsinformatik");
            wib.setDegree("Bachelor of Science (BSc.)");
            wib.setAcronym("3WIB");
            curriculum.add(wib);
            Curriculum wim = new Curriculum();
            wim.setName("Wirtschaftsinformatik");
            wim.setDegree("Master of Science (MSc.)");
            wim.setAcronym("3WIM");
            curriculum.add(wim);
            curriculumService.saveAll(curriculum);
        }
    }

    /**
     * Creates example modules and saves them to the database.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    private void createModules() {
        List<Module> modules = moduleJpaRepository.findAll();

        if (modules.isEmpty()) {
            Module module1 = new Module();
            module1.setCode("0081");
            module1.setTitle("Fortgeschrittene Programmierung");
            module1.setSubtitle("");
            module1.setOfferFrequency("jedes Semester");
            module1.setModuleCoordinator("Prof. Dr. rer. nat. Martin Schmollinger");
            module1.setLecturers("Prof. Dr. rer. nat. Martin Schmollinger");
            module1.setTeachingLanguage("deutsch");
            module1.setCredits(7);
            module1.setPrerequisites("0031");
            module1.setRecommendedPrerequisites("0011, 0041");
            module1.setLearningOutcomes("Vertiefung der Informatik-Grundlagen");
            module1.setContents("Entwicklung objektorientierter Programme");
            module1.setTeachingMethodology("Vorlesung im seminaristischen [sic!] Stil.");
            module1.setReadingList("Sprechen Sie Java?");
            modules.add(module1);

            Module module2 = new Module();
            module2.setCode("0091");
            module2.setTitle("Wirtschaftsmathematik");
            module2.setSubtitle("");
            module2.setOfferFrequency("jedes Semester");
            module2.setModuleCoordinator("Prof. Dr. Bernhard Mößner");
            module2.setLecturers("Frau Gisela Filip");
            module2.setTeachingLanguage("deutsch");
            module2.setCredits(8);
            module2.setPrerequisites("keine");
            module2.setRecommendedPrerequisites("0041");
            module2.setLearningOutcomes("Solide BWL-Grundlagen");
            module2.setContents("Kenntnis der grundlegenden Konzepte");
            module2.setTeachingMethodology("Vorlesung mit begleitendem Praktikum.");
            module2.setReadingList("Mathematik für Wirtschaftswissenschaftler");
            modules.add(module2);
            
            Module module3 = new Module();
            module3.setCode("0090");
            module3.setTitle("Datenbanksysteme");
            module3.setSubtitle("");
            module3.setOfferFrequency("jedes Semester");
            module3.setModuleCoordinator("Prof. Dr. Ilia Petrov");
            module3.setLecturers("Prof. Dr. Ilia Petrov");
            module3.setTeachingLanguage("deutsch");
            module3.setCredits(7);
            module3.setPrerequisites("keine");
            module3.setRecommendedPrerequisites("0031,0041,0081");
            module3.setLearningOutcomes("Datenbanksysteme verstehen und erstellen");
            module3.setContents("Kenntnis der grundlegenden Konzepte");
            module3.setTeachingMethodology("Vorlesung mit begleitendem Praktikum.");
            module3.setReadingList("Datenbanksysteme für Informatiker");
            module3.setExReg(exregJpaRepository.findAll().get(0));
            modules.add(module3);
            
            moduleJpaRepository.saveAll(modules);
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        // TODO Auto-generated method stub
        return running;
    }

    @Override
    public int getPhase() {
        return 1;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        running = false;
        callback.run();
    }
}
