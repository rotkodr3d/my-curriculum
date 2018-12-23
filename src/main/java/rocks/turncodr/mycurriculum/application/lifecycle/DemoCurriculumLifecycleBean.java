package rocks.turncodr.mycurriculum.application.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import rocks.turncodr.mycurriculum.model.AreaOfStudies;
import rocks.turncodr.mycurriculum.model.Curriculum;
import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;
import rocks.turncodr.mycurriculum.services.AreaOfStudiesJpaRepository;
import rocks.turncodr.mycurriculum.services.CurriculumJpaRepository;
import rocks.turncodr.mycurriculum.services.ExRegJpaRepository;
import rocks.turncodr.mycurriculum.services.ModuleJpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Lifecycle bean that creates demo curricula.
 */
@Component
@SuppressWarnings("checkstyle:magicnumber")
public class DemoCurriculumLifecycleBean implements SmartLifecycle {

    private boolean running;

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private CurriculumJpaRepository curriculumService;

    @Autowired
    private ExRegJpaRepository exregJpaRepository;

    @Autowired
    private AreaOfStudiesJpaRepository areaOfStudiesJpaRepository;

    @Override
    public void start() {
        running = true;
        this.createAreaOfStudies();
        this.createExreg();
        this.createModules();
        this.createCurriculum();
        this.createExregWIB();
    }

    /**
     * Creates the WIB ExReg and the modules for the first two semesters and maps them to it.
     */
    private void createExregWIB() {
        ExReg wib = new ExReg();
        wib.setName("Wirtschaftsinformatik Bachelor");
        wib.setValidFrom(Date.valueOf("2018-01-01"));
        wib = exregJpaRepository.save(wib);


        this.createSemester1Wib(wib);
        this.createSemester2Wib(wib);
    }

    private void createSemester2Wib(ExReg wib) {
        Module rechnungswesen = this.createModuleRechnungswesen();
        rechnungswesen.setExReg(wib);
        moduleJpaRepository.save(rechnungswesen);

        Module digitalMarketing = this.createModuleDigitalMarketing();
        digitalMarketing.setExReg(wib);
        moduleJpaRepository.save(digitalMarketing);

        Module wirtschaftsmathematik = this.createModuleWirtschaftsmathematik();
        wirtschaftsmathematik.setExReg(wib);
        moduleJpaRepository.save(wirtschaftsmathematik);

        Module algorithmen = this.createModuleAlgorithmen();
        algorithmen.setExReg(wib);
        moduleJpaRepository.save(algorithmen);

        Module computernetzwerke = this.createModuleComputernetzwerke();
        computernetzwerke.setExReg(wib);
        moduleJpaRepository.save(computernetzwerke);

        Module fortgeschritteneProgrammierung = this.createModuleFortgeschritteneProgrammierung();
        fortgeschritteneProgrammierung.setExReg(wib);
        moduleJpaRepository.save(fortgeschritteneProgrammierung);

    }

    private Module createModuleFortgeschritteneProgrammierung() {
        Module fortgeschritteneProgrammierung = new Module();
        fortgeschritteneProgrammierung.setTitle("Fortgeschrittene Programmierung");
        fortgeschritteneProgrammierung.setCode("WIB26");
        fortgeschritteneProgrammierung.setSubtitle("");
        fortgeschritteneProgrammierung.setOfferFrequency("jedes Semester");
        fortgeschritteneProgrammierung.setModuleCoordinator("Prof. Dr. Martin Schmollinger");
        fortgeschritteneProgrammierung.setLecturers("Prof. Dr. Martin Schmollinger");
        fortgeschritteneProgrammierung.setTeachingLanguage("Deutsch");
        fortgeschritteneProgrammierung.setSemester(2);
        fortgeschritteneProgrammierung.setCredits(5);
        fortgeschritteneProgrammierung.setPrerequisites("Keine");
        fortgeschritteneProgrammierung.setRecommendedPrerequisites("Grundlagen der Informatik");
        fortgeschritteneProgrammierung.setLearningOutcomes("Ziel des Modules ist es die Informatikgrundlagen der Studierenden zu vertiefen.");
        fortgeschritteneProgrammierung.setContents("Im Modul wird die Entwicklung von Desktop-Applikationen mit einer objektorientierten Sprache thematisiert.");
        fortgeschritteneProgrammierung.setTeachingMethodology("Das Modul besteht aus einer Vorlesung in seminaristischem Stil mit Tafelanschrieb.");
        fortgeschritteneProgrammierung.setReadingList("Epple, Anton (2015): JavaFX 8 - Grundlagen und fortgeschrittene Techniken. dpunkt.verlag");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        fortgeschritteneProgrammierung.setAreaOfStudies(informatik);

        return fortgeschritteneProgrammierung;
    }

    private Module createModuleComputernetzwerke() {
        Module computernetzwerke = new Module();
        computernetzwerke.setTitle("Computernetzwerke");
        computernetzwerke.setCode("WIB25");
        computernetzwerke.setSubtitle("");
        computernetzwerke.setOfferFrequency("jedes Semester");
        computernetzwerke.setModuleCoordinator("Prof. Dr. Christian Decker");
        computernetzwerke.setLecturers("Prof. Dr. Christian Decker");
        computernetzwerke.setTeachingLanguage("Deutsch");
        computernetzwerke.setSemester(2);
        computernetzwerke.setCredits(5);
        computernetzwerke.setPrerequisites("Keine");
        computernetzwerke.setRecommendedPrerequisites("Keine");
        computernetzwerke.setLearningOutcomes("Internet Grundlagen und Kommunikationsmodell");
        computernetzwerke.setContents("Das Internet dominiert weltweit die Art und Weise wie wir kommunizieren, Informationen konsumieren und produzieren und Geschäftsprozesse abwickeln.");
        computernetzwerke.setTeachingMethodology("PDF der Folien aus der Vorlesung. Weiteres Material wird während der Vorlesung bekannt gegeben.");
        computernetzwerke.setReadingList("J. F. Kurose, K. W. Ross: Computernetze; Pearson Studium 2014");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        computernetzwerke.setAreaOfStudies(informatik);

        return computernetzwerke;
    }

    private Module createModuleAlgorithmen() {
        Module algorithmen = new Module();
        algorithmen.setTitle("Algorithmen und Datenstrukturen");
        algorithmen.setCode("WIB24");
        algorithmen.setSubtitle("");
        algorithmen.setOfferFrequency("jedes Semester");
        algorithmen.setModuleCoordinator("Prof. Dr. Martin Schmollinger");
        algorithmen.setLecturers("Prof. Dr. Martin Schmollinger, Prof. Dr. Eckhard Ammann");
        algorithmen.setTeachingLanguage("Deutsch");
        algorithmen.setSemester(2);
        algorithmen.setCredits(5);
        algorithmen.setPrerequisites("Keine");
        algorithmen.setRecommendedPrerequisites("Keine");
        algorithmen.setLearningOutcomes("Ziel des Moduls ist es die Informatikgrundlagen der Studierenden zu vertiefen.");
        algorithmen.setContents("Einführung (Algorithmen und Datenstrukturen, Entwurfsmethoden, Komplexität bzw. asymptotische Analyse)");
        algorithmen.setTeachingMethodology("Das Modul besteht aus einer Vorlesung in seminaristischem Stil mit Tafelanschrieb.");
        algorithmen.setReadingList("M.T.Goodrich; R.Tamassia, M.H.Goldwasser: Data Structures and Algorithms in Java");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        algorithmen.setAreaOfStudies(informatik);

        return algorithmen;
    }

    private Module createModuleWirtschaftsmathematik() {
        Module wirtschaftsmathematik = new Module();
        wirtschaftsmathematik.setTitle("Wirtschaftsmathematik und Induktive Statistik");
        wirtschaftsmathematik.setCode("WIB23");
        wirtschaftsmathematik.setSubtitle("");
        wirtschaftsmathematik.setOfferFrequency("jedes Semester");
        wirtschaftsmathematik.setModuleCoordinator("Prof. Dr. Josef Schürle");
        wirtschaftsmathematik.setLecturers("Prof. Dr. Josef Schürle");
        wirtschaftsmathematik.setTeachingLanguage("Deutsch");
        wirtschaftsmathematik.setSemester(2);
        wirtschaftsmathematik.setCredits(5);
        wirtschaftsmathematik.setPrerequisites("Online Testat bestanden");
        wirtschaftsmathematik.setRecommendedPrerequisites("Statistik");
        wirtschaftsmathematik.setLearningOutcomes("Ziel des Moduls ist zum einen die Vermittlung mathematischer Grundlagen, die zur Lösung vielfacher betriebswirtschaftlicher Fragestellungen notwendig sind.");
        wirtschaftsmathematik.setContents("Wirtschaftsmathematik");
        wirtschaftsmathematik.setTeachingMethodology("Vermittlung der theoretischen Grundlagen mittels Beamer-Präsentation, ergänzt durch Tafelanschriebe. Besprechung von Lösungen der Übungsaufgaben.");
        wirtschaftsmathematik.setReadingList("Fahrmeir / Heumann / Künstler / Pigeot / Tutz (2016): Statistik – Der Weg zur Datenanalyse. 8. Auflage. Wiesbaden: SpringerSpektrum.");

        AreaOfStudies mathe = areaOfStudiesJpaRepository.findByName("Mathematik");
        wirtschaftsmathematik.setAreaOfStudies(mathe);

        return wirtschaftsmathematik;
    }

    private Module createModuleDigitalMarketing() {
        Module digitalMarketing = new Module();
        digitalMarketing.setTitle("Digital Marketing and Sales");
        digitalMarketing.setCode("WIB22");
        digitalMarketing.setSubtitle("");
        digitalMarketing.setOfferFrequency("jedes Semester");
        digitalMarketing.setModuleCoordinator("Prof. Dr. Alexander Rossmann");
        digitalMarketing.setLecturers("Prof. Dr. Alexander Rossmann");
        digitalMarketing.setTeachingLanguage("Deutsch");
        digitalMarketing.setSemester(2);
        digitalMarketing.setCredits(5);
        digitalMarketing.setPrerequisites("Keine");
        digitalMarketing.setRecommendedPrerequisites("Keine");
        digitalMarketing.setLearningOutcomes("Das Modul „Digital Marketing and Sales“ bezieht sich auf die Nutzung digitaler Kanäle für unterschiedliche Teilfragen der Marketing- und Vertriebsstrategie.");
        digitalMarketing.setContents("Grundlagen zu Digital Marketing und eCommerce, Kernbegriffe, historische Entwicklung.");
        digitalMarketing.setTeachingMethodology("Vorlesung, Übungsaufgaben, Fallstudien, Skript mit PPT-Folien, beispielhafte Publikationen, Hausarbeiten, Präsentationen, Projektarbeiten.");
        digitalMarketing.setReadingList("Chaffey, D., Smith, P. R., & Smith, P. R. (2012). eMarketing eXcellence: Planning and optimizing your digital marketing. Routledge.");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        digitalMarketing.setAreaOfStudies(bwl);

        return digitalMarketing;
    }

    private Module createModuleRechnungswesen() {
        Module rechnungswesen = new Module();
        rechnungswesen.setTitle("Betriebliches Rechnungswesen");
        rechnungswesen.setCode("WIB21");
        rechnungswesen.setSubtitle("");
        rechnungswesen.setOfferFrequency("jedes Semester");
        rechnungswesen.setModuleCoordinator("Prof. Dr. N.N");
        rechnungswesen.setLecturers("Prof. Dr. N.N");
        rechnungswesen.setTeachingLanguage("Deutsch");
        rechnungswesen.setSemester(2);
        rechnungswesen.setCredits(5);
        rechnungswesen.setPrerequisites("Keine");
        rechnungswesen.setRecommendedPrerequisites("Keine");
        rechnungswesen.setLearningOutcomes("Ziel des Moduls ist es, den Studierenden eine fundierte Einführung in das interne und externe Rechnungswesen eines Unternehmens zu geben");
        rechnungswesen.setContents("Grundlagen des betrieblichen Rechnungswesens");
        rechnungswesen.setTeachingMethodology("Vermittlung der theoretischen Grundlagen mittels Beamer-Präsentation, ergänzt durch Tafelanschriebe. Besprechung von Lösungen der Übungsaufgaben.");
        rechnungswesen.setReadingList("Coenenberg/Haller/Mattner/Schultze: Einführung in das Rechnungswesen - Grundlagen der Buchführung und Bilanzierung. 6. Auflage. Stuttgart: Schäffer-Poeschel");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        rechnungswesen.setAreaOfStudies(bwl);

        return rechnungswesen;
    }

    private void createSemester1Wib(ExReg wib) {
        Module bwl = this.createModuleGrundlagenBWL();
        bwl.setExReg(wib);
        moduleJpaRepository.save(bwl);

        Module entrepreneurship = this.createModuleEntrepreneurship();
        entrepreneurship.setExReg(wib);
        moduleJpaRepository.save(entrepreneurship);

        Module statistik = this.createModuleStatistik();
        statistik.setExReg(wib);
        moduleJpaRepository.save(statistik);

        Module diskreteMathematik = this.createModuleDiskreteMathematik();
        diskreteMathematik.setExReg(wib);
        moduleJpaRepository.save(diskreteMathematik);

        Module grundlagenInformatik = this.createModuleGrundlagenInformatik();
        grundlagenInformatik.setExReg(wib);
        moduleJpaRepository.save(grundlagenInformatik);

        Module praktikumProgrammieren = this.createModulePraktikumProgrammieren();
        praktikumProgrammieren.setExReg(wib);
        moduleJpaRepository.save(praktikumProgrammieren);
    }

    private Module createModulePraktikumProgrammieren() {
        Module praktikumProgrammieren = new Module();
        praktikumProgrammieren.setTitle("Praktikum Programmierung");
        praktikumProgrammieren.setCode("WIB16");
        praktikumProgrammieren.setSubtitle("");
        praktikumProgrammieren.setOfferFrequency("jedes Semester");
        praktikumProgrammieren.setModuleCoordinator("Prof. Dr. Wolfgang Blochinger");
        praktikumProgrammieren.setLecturers("Prof. Dr. Wolfgang Blochinger");
        praktikumProgrammieren.setTeachingLanguage("Deutsch");
        praktikumProgrammieren.setSemester(1);
        praktikumProgrammieren.setCredits(5);
        praktikumProgrammieren.setPrerequisites("Keine");
        praktikumProgrammieren.setRecommendedPrerequisites("Keine");
        praktikumProgrammieren.setLearningOutcomes("Dieses Modul soll Studierenden die Grundlagen der objektorientierten Programmierung der Programmiersprache Java vermitteln und sie in die Lage versetzen, selbständig einfachere Java Programme   zu   entwerfen   und   zu   implementieren.");
        praktikumProgrammieren.setContents("Klassen, Objekte, Datenfelder, Konstruktoren, Methoden");
        praktikumProgrammieren.setTeachingMethodology("Betreute Rechnerübungen, Folien, Tafel");
        praktikumProgrammieren.setReadingList("David J. Barnes und Michael Kölling (2017): Java  lernen  mit  BlueJ:  Objects  first - Eine Einführung in Java. 6. Auflage. Pearson Studium.");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        praktikumProgrammieren.setAreaOfStudies(informatik);

        return praktikumProgrammieren;
    }

    private Module createModuleGrundlagenInformatik() {
        Module grundlagenInformatik = new Module();
        grundlagenInformatik.setTitle("Grundlagen Informatik");
        grundlagenInformatik.setCode("WIB15");
        grundlagenInformatik.setSubtitle("");
        grundlagenInformatik.setOfferFrequency("jedes Semester");
        grundlagenInformatik.setModuleCoordinator("Prof. Dr. Wolfgang Blochinger");
        grundlagenInformatik.setLecturers("Prof. Dr. Eckhard Ammann, "
                + "Prof. Dr. Wolfgang Blochinger, "
                + "Prof. Dr. Christian Decker, "
                + "Prof. Dr. Bernhard Mößner, "
                + "Prof. Dr. Jürgen Münch, "
                + "Prof. Dr. Ilia Petrov, "
                + "Prof. Dr. Martin Schmollinger");
        grundlagenInformatik.setTeachingLanguage("Deutsch");
        grundlagenInformatik.setSemester(1);
        grundlagenInformatik.setCredits(5);
        grundlagenInformatik.setPrerequisites("Keine");
        grundlagenInformatik.setRecommendedPrerequisites("Keine");
        grundlagenInformatik.setLearningOutcomes("Dieses Modul soll Studierenden die grundlegenden Konzepte und Methoden der Informatik vermitteln.");
        grundlagenInformatik.setContents("Darstellung und Interpretation von Information");
        grundlagenInformatik.setTeachingMethodology("Folien, Tafel, Praktische Demonstrationen und Übungen");
        grundlagenInformatik.setReadingList("Helmut Herold, Bruno Lurz und Jürgen Wohlrab (2012): Grundlagen  der  Informatik.  2. Auflage. Pearson Studium.");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        grundlagenInformatik.setAreaOfStudies(informatik);

        return grundlagenInformatik;
    }

    private Module createModuleDiskreteMathematik() {
        Module diskreteMathematik = new Module();
        diskreteMathematik.setTitle("Diskrete Mathematik");
        diskreteMathematik.setCode("WIB14");
        diskreteMathematik.setSubtitle("");
        diskreteMathematik.setOfferFrequency("jedes Semester");
        diskreteMathematik.setModuleCoordinator("Prof. Dr. Bernhard Mößner");
        diskreteMathematik.setLecturers("Prof. Dr. Bernhard Mößner");
        diskreteMathematik.setTeachingLanguage("Deutsch");
        diskreteMathematik.setSemester(1);
        diskreteMathematik.setCredits(5);
        diskreteMathematik.setPrerequisites("Online Testat bestanden");
        diskreteMathematik.setRecommendedPrerequisites("Keine");
        diskreteMathematik.setLearningOutcomes("Die Studierenden können grundlegende Begriffe und Methoden der diskreten Mathematik anwenden.");
        diskreteMathematik.setContents("Im Bereich der Logik werden Aussagenlogik, aussagenlogische Verknüpfungen, Wahrheitstabellen, semantische Äquivalenz von Aussagen, Prädikatenlogik, Quantoren und Negation von Quantoren behandelt.");
        diskreteMathematik.setTeachingMethodology("Das Modul besteht aus einer zweistündigen Vorlesung mit einem zweistündigen Praktikum.");
        diskreteMathematik.setReadingList("C. Meinel und M. Mundhenk (2015): Mathematische Grundlagen der Informatik: Mathematisches Denken und Beweisen. Eine Einführung. 6. überarb. Auflage. Wiesbaden: Springer Vieweg.");

        AreaOfStudies mathe = areaOfStudiesJpaRepository.findByName("Mathematik");
        diskreteMathematik.setAreaOfStudies(mathe);

        return diskreteMathematik;
    }

    private Module createModuleStatistik() {
        Module statistik = new Module();
        statistik.setTitle("Statistik");
        statistik.setCode("WIB13");
        statistik.setSubtitle("");
        statistik.setOfferFrequency("jedes Semester");
        statistik.setModuleCoordinator("Prof. Dr. Christian Decker");
        statistik.setLecturers("Prof. Dr. Christian Decker");
        statistik.setTeachingLanguage("Deutsch");
        statistik.setSemester(1);
        statistik.setCredits(5);
        statistik.setPrerequisites("Online Testat bestanden");
        statistik.setRecommendedPrerequisites("Keine");
        statistik.setLearningOutcomes("Die Studierenden lernen Teilgebiete von Mathematik und Statistik und deren Denkweisen kennen.");
        statistik.setContents("Es werden die mathematischen Begriffe, Methoden und Instrumente für einen formalen Umgang mit Beobachtungsdaten vermittelt.");
        statistik.setTeachingMethodology("Vorlesung mit begleitenden Übungen sowie seminaristischer Unterricht mit Tafelanschrieb.");
        statistik.setReadingList("Nollau/Partzsch/Storm/Lange: Wahrscheinlichkeitsrechnung und Statistik  in  Beispielen und Aufgaben,  Teubner (1997).");

        AreaOfStudies mathe = areaOfStudiesJpaRepository.findByName("Mathematik");
        statistik.setAreaOfStudies(mathe);

        return statistik;
    }

    private Module createModuleEntrepreneurship() {
        Module entrepreneurship = new Module();
        entrepreneurship.setTitle("Entrepreneurship");
        entrepreneurship.setCode("WIB12");
        entrepreneurship.setSubtitle("");
        entrepreneurship.setOfferFrequency("jedes Semester");
        entrepreneurship.setModuleCoordinator("Prof. Dr. Jürgen Münch");
        entrepreneurship.setLecturers("Prof. Dr. Jürgen Münch");
        entrepreneurship.setTeachingLanguage("Deutsch");
        entrepreneurship.setSemester(1);
        entrepreneurship.setCredits(5);
        entrepreneurship.setPrerequisites("Keine");
        entrepreneurship.setRecommendedPrerequisites("Keine");
        entrepreneurship.setLearningOutcomes("Das Modul Entrepreneurship vermittelt Ihnen wesentliche Prinzipien, Methoden und Erkenntnisse des Intra- und Entrepreneurships.");
        entrepreneurship.setContents("Techniken, Methoden, Werkzeuge und organisatorische Aspekte des Intra-und Entrepreneurships");
        entrepreneurship.setTeachingMethodology("Vorlesung mit begleitenden Übungen");
        entrepreneurship.setReadingList("Marya, A. (2012): Running Lean: Iterate from Plan A to a Plan That Works. 2. Auflage, O'Reilly.");

        AreaOfStudies methoden = areaOfStudiesJpaRepository.findByName("Methoden");
        entrepreneurship.setAreaOfStudies(methoden);

        return entrepreneurship;
    }

    private Module createModuleGrundlagenBWL() {
        Module grundlagenBWL = new Module();
        grundlagenBWL.setTitle("Grundlagen der Betriebswirtschaftslehre");
        grundlagenBWL.setCode("WIB11");
        grundlagenBWL.setSubtitle("");
        grundlagenBWL.setOfferFrequency("jedes Semester");
        grundlagenBWL.setModuleCoordinator("Prof. Dr. Josef Schürle");
        grundlagenBWL.setLecturers("Prof. Dr. Josef Schürle");
        grundlagenBWL.setTeachingLanguage("Deutsch");
        grundlagenBWL.setSemester(1);
        grundlagenBWL.setCredits(5);
        grundlagenBWL.setPrerequisites("Keine");
        grundlagenBWL.setRecommendedPrerequisites("Keine");
        grundlagenBWL.setLearningOutcomes(
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
        grundlagenBWL.setContents("Grundbegriffe und Erfolgsmaßstäbe der Betriebswirtschaftslehre\n "
                + "Betriebswirtschaftliche Zielkonzeption\n "
                + "Grundlagen der wertorientierten Unternehmensführung\n "
                + "Rechtsformen und deren betriebswirtschaftliche Konsequenzen \n "
                + "(insb. Unternehmensführung, Gewinnverteilung, Haftung, Finanzierung und Steuern)\n "
                + "Unternehmenszusammenschlüsse\n "
                + "Ausgewählte Aspekte der Unternehmensführung (Organisation, Personal)");
        grundlagenBWL.setTeachingMethodology(
                "Vermittlung der theoretischen Grundlagen mittels Beamer-Präsentation, ergänzt durch \n "
                        + "Tafelanschriebe. Gemeinsame Besprechung und Analyse aktueller wirtschaftlicher Ereignisse \n "
                        + "anhand von Presseartikeln. Studierende erarbeiten Lösungen zu Übungsaufgaben in \n "
                        + "Gruppenarbeit und präsentieren ihre Ergebnisse im Plenum");
        grundlagenBWL.setReadingList("Jung (2013): Allgemeine Betriebswirtschaftslehre. 13. Auflage. Berlin: De Gruyter.\n"
                + "Wöhe (2016): Einführung in die Allgemeine Betriebswirtschaftslehre. 26. Auflage. München: Vahlen\n"
                + "Wöhe / Kaiser / Döring (2016): Übungsbuch zur allgemeinen Betriebswirtschaftslehre. 15. Auflage. München: Vahlen");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        grundlagenBWL.setAreaOfStudies(bwl);

        return grundlagenBWL;
    }
    
    @SuppressWarnings("checkstyle:magicnumber")
    private void createExreg() {
        ExReg wib = new ExReg();
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
            module1.setAreaOfStudies(areaOfStudiesJpaRepository.findByName("Informatik"));
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
            module2.setAreaOfStudies(areaOfStudiesJpaRepository.findByName("BWL"));
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
            module3.setSemester(3);
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
            
            Module module4 = new Module();
            module4.setCode("0080");
            module4.setTitle("Webprogrammierung");
            module4.setSemester(4);
            module4.setSubtitle("");
            module4.setOfferFrequency("jedes Semester");
            module4.setModuleCoordinator("M.Sc. Matthias Gutbrod");
            module4.setLecturers("M.Sc. Matthias Gutbrod");
            module4.setTeachingLanguage("englisch");
            module4.setCredits(7);
            module4.setPrerequisites("keine");
            module4.setRecommendedPrerequisites("0031,0041,0081");
            module4.setLearningOutcomes("Grundlagen zur Programmierung von Webseiten.");
            module4.setContents("Kenntnis der grundlegenden Konzepte");
            module4.setTeachingMethodology("Vorlesung mit begleitendem Praktikum.");
            module4.setReadingList("HTML 5, PHP für Dummies, JavaScript für Dummies.");
            module4.setExReg(exregJpaRepository.findAll().get(0));
            modules.add(module4);
            
            moduleJpaRepository.saveAll(modules);
        }
    }

    @SuppressWarnings("checkstyle:magicnumber")
    private void createAreaOfStudies() {
        List<AreaOfStudies> areaOfStudies = areaOfStudiesJpaRepository.findAll();
        if (areaOfStudies.isEmpty()) {
            AreaOfStudies informatik = new AreaOfStudies();
            informatik.setName("Informatik");
            informatik.setColorRGB("253,235,9");
            areaOfStudiesJpaRepository.save(informatik);

            AreaOfStudies bwl = new AreaOfStudies();
            bwl.setName("BWL");
            bwl.setColorRGB("255,65,60");
            areaOfStudiesJpaRepository.save(bwl);

            AreaOfStudies mathe = new AreaOfStudies();
            mathe.setName("Mathematik");
            mathe.setColorRGB("0,176,240");
            areaOfStudiesJpaRepository.save(mathe);

            AreaOfStudies methoden = new AreaOfStudies();
            methoden.setName("Methoden");
            methoden.setColorRGB("255,197,0");
            areaOfStudiesJpaRepository.save(methoden);
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
