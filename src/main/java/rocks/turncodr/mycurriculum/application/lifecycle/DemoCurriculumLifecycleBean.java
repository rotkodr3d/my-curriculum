package rocks.turncodr.mycurriculum.application.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import rocks.turncodr.mycurriculum.model.Curriculum;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.CurriculumJpaRepository;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Lifecycle bean that creates demo curricula.
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
        this.createModules();
        this.createCurriculum();
        this.createExregWIB();
    }

    @SuppressWarnings("checkstyle:magicnumber")
    private void createExregWIB() {
        ExReg wib = new ExReg();
        wib.setId(1);
        wib.setName("Wirtschaftsinformatik Bachelor");
        wib.setValidFrom(Date.valueOf("2018-01-01"));
        wib = exregJpaRepository.save(wib);
        System.out.println("WIB ID: " + wib.getId());

        Module bwl = new Module();
        bwl.setTitle("Grundlagen der Betriebswirtschaftslehre");
        bwl.setCode("WIB11");
        bwl.setSubtitle("");
        bwl.setOfferFrequency("jedes Semester");
        bwl.setModuleCoordinator("Prof. Dr. Josef Schürle");
        bwl.setLecturers("Prof. Dr. Josef Schürle");
        bwl.setTeachingLanguage("Deutsch");
        bwl.setSemester(1);
        bwl.setCredits(5);
        bwl.setPrerequisites("Keine");
        bwl.setRecommendedPrerequisites("Keine");
        bwl.setLearningOutcomes(
                "Die Studierenden kennen grundlegende Begriffe aus der Betriebswirtschaftslehre und verstehen \n"
                        + "ihre  Bedeutung.  Die  Studierenden  verstehen  betriebswirtschaftliche  Zielkonzeptionen  sowie \n"
                        + "insbesondere die wertorientierte Unternehmensführung als zentralen Erfolgsmaßstab. Die \n"
                        + "Studierenden kennen wesentliche Eigenschaften der bedeutendsten Rechtsformen in Deutschland \n"
                        + "und sind in der Lage, die sich aus den jeweiligen Rechtsformen ergebenden \n"
                        + "betriebswirtschaftlichen Konsequenzen zu beurteilen. Die Studierenden kennen unterschiedliche \n"
                        + "Formen der Kooperation von Unternehmen bzw. Formen von Unternehmenszusammenschlüssen \n"
                        + "sowie ausgewählte Aspekte der Unternehmensführung.\n\n"
                        + "Fertigkeiten:\n"
                        + "Die  Studierenden  wenden  das  theoretische  Fachwissen  auf  konkrete  betriebswirtschaftliche \n"
                        + "Fragestellungen  an.  Sie  sind  in  der  Lage,  quantitative  Ergebnisse  abzuleiten,  anhand  derer\n"
                        + "Entscheidungsalternativen zu beurteilen und da\n"
                        + "raus Entscheidungsvorschläge abzuleiten.\n"
                        + "Kompetenzen:\n"
                        + "Die Studierenden denken in wirtschaftlichen Zusammenhängen und sind in der Lage, sich \n"
                        + "bezüglich grundlegender betriebswirtschaftlicher Sachverhalte eine fundierte Meinung zu bilden. \n"
                        + "Sie verstehen die \ngrundlegenden Konzepte zur Beurteilung wirtschaftlichen Erfolgs sowie den \n"
                        + "Zusammengang zwischen Ergebnis und Risikoverteilung.Die Studierenden sind in der Lage, \n"
                        + "dieses Wissen auf ihr Handeln zu überragen.");
        bwl.setContents("Grundbegriffe und Erfolgsmaßstäbe der Betriebswirtschaftslehre\n "
                + "Betriebswirtschaftliche Zielkonzeption\n "
                + "Grundlagen der wertorientierten Unternehmensführung\n "
                + "Rechtsformen und deren betriebswirtschaftliche Konsequenzen \n "
                + "(insb. Unternehmensführung, Gewinnverteilung, Haftung, Finanzierung und Steuern)\n "
                + "Unternehmenszusammenschlüsse\n "
                + "Ausgewählte Aspekte der Unternehmensführung (Organisation, Personal)");
        bwl.setTeachingMethodology(
                "Vermittlung der theoretischen Grundlagen mittels Beamer-Präsentation, ergänzt durch \n "
                        + "Tafelanschriebe. Gemeinsame Besprechung und Analyse aktueller wirtschaftlicher Ereignisse \n "
                        + "anhand von Presseartikeln. Studierende erarbeiten Lösungen zu Übungsaufgaben in \n "
                        + "Gruppenarbeit und präsentieren ihre Ergebnisse im Plenum");
        bwl.setReadingList("Jung (2013): Allgemeine Betriebswirtschaftslehre. 13. Auflage. Berlin: De Gruyter.\n"
                + "Wöhe (2016): Einführung in die Allgemeine Betriebswirtschaftslehre. 26. Auflage. München: Vahlen\n"
                + "Wöhe / Kaiser / Döring (2016): Übungsbuch zur allgemeinen Betriebswirtschaftslehre. 15. Auflage. München: Vahlen");
        bwl.setExReg(wib);

        moduleJpaRepository.save(bwl);
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
