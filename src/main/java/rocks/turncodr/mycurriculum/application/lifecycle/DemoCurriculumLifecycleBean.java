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
        this.createModules();
        this.createCurriculum();
        this.createExregWIB();
    }

    /**
     * Creates the WIB ExReg and the modules for the first two semesters and maps
     * them to it.
     */
    private void createExregWIB() {
        List<Curriculum> curriculum = curriculumService.findAll();
        List<ExReg> exregs = exregJpaRepository.findAll();
        
        if (exregs.isEmpty()) {
            ExReg wib = new ExReg();
            wib.setName("Wirtschaftsinformatik Bachelor");
            wib.setValidFrom(Date.valueOf("2018-01-01"));
            wib.setCurriculum(curriculum.stream().findFirst().get());
            wib = exregJpaRepository.save(wib);
            System.out.println(wib.getId());
    
            this.createSemester1Wib(wib);
            this.createSemester2Wib(wib);
            this.createSemester3Wib(wib);
            this.createSemester4Wib(wib);
            this.createSemester5Wib(wib);
            this.createSemester6Wib(wib);
            this.createSemester7Wib(wib);
        }
    }

    private void createSemester7Wib(ExReg wib) {
        Module wissenschaftlichesArbeiten = this.createModuleWissenschaftlichesArbeiten();
        wissenschaftlichesArbeiten.setExReg(wib);
        moduleJpaRepository.save(wissenschaftlichesArbeiten);

        Module bachelorThesis = this.createModuleBachelorThesis();
        bachelorThesis.setExReg(wib);
        moduleJpaRepository.save(bachelorThesis);

        Module wahlpflichtmodul2 = this.createModuleWahlpflichtmodul2();
        wahlpflichtmodul2.setExReg(wib);
        moduleJpaRepository.save(wahlpflichtmodul2);

        Module wahlpflichtmodul3 = this.createModuleWahlpflichtmodul3();
        wahlpflichtmodul3.setExReg(wib);
        moduleJpaRepository.save(wahlpflichtmodul3);

        Module wahlpflichtmodul4 = this.createModuleWahlpflichtmodul4();
        wahlpflichtmodul4.setExReg(wib);
        moduleJpaRepository.save(wahlpflichtmodul4);

    }

    private Module createModuleWahlpflichtmodul4() {
        Module wahlpflichtmodul4 = new Module();
        wahlpflichtmodul4.setTitle("Wahlpflichtmodul 4");
        wahlpflichtmodul4.setCode("WIB73");
        wahlpflichtmodul4.setSubtitle("");
        wahlpflichtmodul4.setOfferFrequency("jedes Semester");
        wahlpflichtmodul4.setModuleCoordinator("Studiendekan (Prof. Dr. Josef Schürle)");
        wahlpflichtmodul4.setLecturers("Alle Dozenten der Fakultät");
        wahlpflichtmodul4.setTeachingLanguage("Deutsch");
        wahlpflichtmodul4.setSemester(7);
        wahlpflichtmodul4.setCredits(5);
        wahlpflichtmodul4.setPrerequisites("Keine");
        wahlpflichtmodul4.setRecommendedPrerequisites("keine");
        wahlpflichtmodul4.setLearningOutcomes("Die Wahlfachangebote erlauben es den Studierenden, ihre persönlichen Neigungen zu vertiefen und das persönliche Lernportfolio entweder um bisher unbekannte Themen aus der Wirtschaftsinformatik oder um Inhalte aus angrenzenden Fachdisziplinen zu erweitern.");
        wahlpflichtmodul4.setContents("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul4.setTeachingMethodology("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul4.setReadingList("Abhängig von der gewählten Veranstaltung.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        wahlpflichtmodul4.setAreaOfStudies(wahl);

        return wahlpflichtmodul4;
    }

    private Module createModuleWahlpflichtmodul3() {
        Module wahlpflichtmodul3 = new Module();
        wahlpflichtmodul3.setTitle("Wahlpflichtmodul 3");
        wahlpflichtmodul3.setCode("WIB72");
        wahlpflichtmodul3.setSubtitle("");
        wahlpflichtmodul3.setOfferFrequency("jedes Semester");
        wahlpflichtmodul3.setModuleCoordinator("Studiendekan (Prof. Dr. Josef Schürle)");
        wahlpflichtmodul3.setLecturers("Alle Dozenten der Fakultät");
        wahlpflichtmodul3.setTeachingLanguage("Deutsch");
        wahlpflichtmodul3.setSemester(7);
        wahlpflichtmodul3.setCredits(5);
        wahlpflichtmodul3.setPrerequisites("Keine");
        wahlpflichtmodul3.setRecommendedPrerequisites("keine");
        wahlpflichtmodul3.setLearningOutcomes("Die Wahlfachangebote erlauben es den Studierenden, ihre persönlichen Neigungen zu vertiefen und das persönliche Lernportfolio entweder um bisher unbekannte Themen aus der Wirtschaftsinformatik oder um Inhalte aus angrenzenden Fachdisziplinen zu erweitern.");
        wahlpflichtmodul3.setContents("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul3.setTeachingMethodology("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul3.setReadingList("Abhängig von der gewählten Veranstaltung.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        wahlpflichtmodul3.setAreaOfStudies(wahl);

        return wahlpflichtmodul3;
    }

    private Module createModuleWahlpflichtmodul2() {
        Module wahlpflichtmodul2 = new Module();
        wahlpflichtmodul2.setTitle("Wahlpflichtmodul 2");
        wahlpflichtmodul2.setCode("WIB71");
        wahlpflichtmodul2.setSubtitle("");
        wahlpflichtmodul2.setOfferFrequency("jedes Semester");
        wahlpflichtmodul2.setModuleCoordinator("Studiendekan (Prof. Dr. Josef Schürle)");
        wahlpflichtmodul2.setLecturers("Alle Dozenten der Fakultät");
        wahlpflichtmodul2.setTeachingLanguage("Deutsch");
        wahlpflichtmodul2.setSemester(7);
        wahlpflichtmodul2.setCredits(5);
        wahlpflichtmodul2.setPrerequisites("Keine");
        wahlpflichtmodul2.setRecommendedPrerequisites("keine");
        wahlpflichtmodul2.setLearningOutcomes("Die Wahlfachangebote erlauben es den Studierenden, ihre persönlichen Neigungen zu vertiefen und das persönliche Lernportfolio entweder um bisher unbekannte Themen aus der Wirtschaftsinformatik oder um Inhalte aus angrenzenden Fachdisziplinen zu erweitern.");
        wahlpflichtmodul2.setContents("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul2.setTeachingMethodology("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul2.setReadingList("Abhängig von der gewählten Veranstaltung.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        wahlpflichtmodul2.setAreaOfStudies(wahl);

        return wahlpflichtmodul2;
    }

    private Module createModuleBachelorThesis() {
        Module bachelorThesis = new Module();
        bachelorThesis.setTitle("Bachelor Thesis");
        bachelorThesis.setCode("WIB75");
        bachelorThesis.setSubtitle("");
        bachelorThesis.setOfferFrequency("jedes Semester");
        bachelorThesis.setModuleCoordinator("Studiendekan (Prof. Dr. Josef Schürle)");
        bachelorThesis.setLecturers("Alle Dozenten der Fakultät");
        bachelorThesis.setTeachingLanguage("Deutsch");
        bachelorThesis.setSemester(7);
        bachelorThesis.setCredits(12);
        bachelorThesis.setPrerequisites("Mindestens 140 ECTS, Modul Auslandssemester oder Spezialisierungssemester wurde absolviert");
        bachelorThesis.setRecommendedPrerequisites("keine");
        bachelorThesis.setLearningOutcomes("Die Bachelor-Thesis ist eine abschließende Prüfungsarbeit, mit der die Studierenden nachweisen, dass sie eine interdisziplinäre Aufgabenstellung der Wirtschaftsinformatik selbstständig nach grundlegenden wissenschaftlichen Methoden in einem vorgegebenen Zeitrahmen bearbeiten können.");
        bachelorThesis.setContents("Themen von Bachelorarbeiten beziehen sich auf Aufgabenstellungen der Wirtschaftsinformatik, die aktuell und in der absehbaren Zukunft in der Disziplin relevant sind.");
        bachelorThesis.setTeachingMethodology("Fachliche und methodische Betreuung der Bachelorarbeit durch Beratungs-Betreuungsgespräche, die bei unternehmensnahen Arbeiten auch vor Ort stattfinden.");
        bachelorThesis.setReadingList("Abhängig von der jeweiligen Aufgabenstellung.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        bachelorThesis.setAreaOfStudies(wahl);

        return bachelorThesis;
    }

    private Module createModuleWissenschaftlichesArbeiten() {
        Module wissenschaftlichesArbeiten = new Module();
        wissenschaftlichesArbeiten.setTitle("Wissenschaftliches Arbeiten");
        wissenschaftlichesArbeiten.setCode("WIB74");
        wissenschaftlichesArbeiten.setSubtitle("");
        wissenschaftlichesArbeiten.setOfferFrequency("jedes Semester");
        wissenschaftlichesArbeiten.setModuleCoordinator("Frau Christa Biberacher");
        wissenschaftlichesArbeiten.setLecturers("Frau Christa Biberacher");
        wissenschaftlichesArbeiten.setTeachingLanguage("Deutsch");
        wissenschaftlichesArbeiten.setSemester(7);
        wissenschaftlichesArbeiten.setCredits(3);
        wissenschaftlichesArbeiten.setPrerequisites("Keine");
        wissenschaftlichesArbeiten.setRecommendedPrerequisites("Alle Module der ersten 4 Semester");
        wissenschaftlichesArbeiten.setLearningOutcomes("Das Modul Wissenschaftliches Arbeiten lehrt Kompetenzen zum wissenschaftlichen Lesen und Schreiben und führt die Studierenden an die Bearbeitung der Bachelor-Thesis heran.");
        wissenschaftlichesArbeiten.setContents("Empirische und formale Methoden");
        wissenschaftlichesArbeiten.setTeachingMethodology("-");
        wissenschaftlichesArbeiten.setReadingList("Balzert, Helmut; Schröder, Marion; Schäfer, Christian (2013): Wissenschaftliches Arbeiten. 2. Auflage, Dortmund: W3L");

        AreaOfStudies methoden = areaOfStudiesJpaRepository.findByName("Methoden");
        wissenschaftlichesArbeiten.setAreaOfStudies(methoden);

        return wissenschaftlichesArbeiten;
    }

    private void createSemester6Wib(ExReg wib) {
        Module auslandssemester = this.createModuleAuslandssemester();
        auslandssemester.setExReg(wib);
        moduleJpaRepository.save(auslandssemester);

        //this is an alternative to "auslandssemester". alternative lectures are currently not supported
        //until it's supported, it breaks /exreg/overview
        //Module spezialisierungssemester = this.createModuleSpezialisierungssemester();
        //spezialisierungssemester.setExReg(wib);
        //moduleJpaRepository.save(spezialisierungssemester);

    }

    private Module createModuleSpezialisierungssemester() {
        Module spezialisierungssemester = new Module();
        spezialisierungssemester.setTitle("Spezialisierungssemester");
        spezialisierungssemester.setCode("WIB62");
        spezialisierungssemester.setSubtitle("");
        spezialisierungssemester.setOfferFrequency("jedes Semester");
        spezialisierungssemester.setModuleCoordinator("Studiendekan (Prof. Dr. Josef Schürle)");
        spezialisierungssemester.setLecturers("Dozenten der Hochschule Reutlingen");
        spezialisierungssemester.setTeachingLanguage("Abhängig von besuchten Modulen");
        spezialisierungssemester.setSemester(6);
        spezialisierungssemester.setCredits(30);
        spezialisierungssemester.setPrerequisites("Es werden in Summe weniger als 15 ECTS-Punkte an ausländischen Hochschule erreicht");
        spezialisierungssemester.setRecommendedPrerequisites("Keine");
        spezialisierungssemester.setLearningOutcomes("Werden an ausländischen Hochschule in Summe weniger als 15 ECTS-Punkte erreicht, so muss stattdessen das Modul Spezialisierungssemester absolviert werden.");
        spezialisierungssemester.setContents("Abhängig von den im Learning Agreement vereinbarten Veranstaltungen.");
        spezialisierungssemester.setTeachingMethodology("Abhängig von den im Learning Agreement vereinbarten Veranstaltungen.");
        spezialisierungssemester.setReadingList("Abhängig von den im Learning Agreement vereinbarten Veranstaltungen.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        spezialisierungssemester.setAreaOfStudies(wahl);

        return spezialisierungssemester;
    }

    private Module createModuleAuslandssemester() {
        Module auslandssemester = new Module();
        auslandssemester.setTitle("Auslandssemester");
        auslandssemester.setCode("WIB61");
        auslandssemester.setSubtitle("Studienaufenthalt an einer ausländischen Hochschule");
        auslandssemester.setOfferFrequency("jedes Semester");
        auslandssemester.setModuleCoordinator("Prof. Dr. Dietmar Bönke");
        auslandssemester.setLecturers("Diverse, vornehmlich von Lehrenden der ausländischen Hochschule, an der der Studienaufenthalt durchgeführt wird");
        auslandssemester.setTeachingLanguage("Sprache, in der an der ausländischen Hochschule, an der der Studienaufenthalt durchgeführt wird, gelehrt");
        auslandssemester.setSemester(6);
        auslandssemester.setCredits(30);
        auslandssemester.setPrerequisites("Bestandene Zwischenprüfung");
        auslandssemester.setRecommendedPrerequisites("Keine");
        auslandssemester.setLearningOutcomes("Ziele des Studienaufenthaltes an einer ausländischen Hochschule im sechsten Semester ist, Kenntnisse und Fähigkeiten zu erwerben, die es dem Studierenden erlauben, sich selbständig in einem fremden Kulturkreis zu bewegen.");
        auslandssemester.setContents("Die Inhalte eines Auslandsstudiums werden in einem Learning Agreement vorab vereinbart. Diese passen inhaltlich in das Curriculum des 6.Semester der Wirtschaftsinformatik in Reutlingen.");
        auslandssemester.setTeachingMethodology("Diverse Medienformen, die von der Lehrenden der ausländischen Hochschule, an der der Studienaufenthalt durchgeführt wird, bestimmt werden.");
        auslandssemester.setReadingList("Literaturverzeichnisse zu den jeweiligen Lehrveranstaltungen der Gasthochschule. ");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        auslandssemester.setAreaOfStudies(wahl);

        return auslandssemester;
    }

    private void createSemester5Wib(ExReg wib) {
        Module corporateFinance = this.createModuleCorporateFinance();
        corporateFinance.setExReg(wib);
        moduleJpaRepository.save(corporateFinance);

        Module managementUndControlling = this.createModuleManagementUndControlling();
        managementUndControlling.setExReg(wib);
        moduleJpaRepository.save(managementUndControlling);

        Module businessConsulting = this.createModuleBusinessConsulting();
        businessConsulting.setExReg(wib);
        moduleJpaRepository.save(businessConsulting);

        Module systemeUndSicherheit = this.createModuleSystemeUndSicherheit();
        systemeUndSicherheit.setExReg(wib);
        moduleJpaRepository.save(systemeUndSicherheit);

        Module verteilteSysteme = this.createModuleVerteilteSysteme();
        verteilteSysteme.setExReg(wib);
        moduleJpaRepository.save(verteilteSysteme);

        Module wahlpflichtmodul = this.createModuleWahlpflichtmodul();
        wahlpflichtmodul.setExReg(wib);
        moduleJpaRepository.save(wahlpflichtmodul);

    }

    private Module createModuleWahlpflichtmodul() {
        Module wahlpflichtmodul = new Module();
        wahlpflichtmodul.setTitle("Wahlpflichtmodul 1");
        wahlpflichtmodul.setCode("WIB55");
        wahlpflichtmodul.setSubtitle("");
        wahlpflichtmodul.setOfferFrequency("jedes Semester");
        wahlpflichtmodul.setModuleCoordinator("Studiendekan (Prof. Dr. Josef Schürle)");
        wahlpflichtmodul.setLecturers("Alle Dozenten der Fakultät");
        wahlpflichtmodul.setTeachingLanguage("Deutsch");
        wahlpflichtmodul.setSemester(5);
        wahlpflichtmodul.setCredits(5);
        wahlpflichtmodul.setPrerequisites("Keine");
        wahlpflichtmodul.setRecommendedPrerequisites("keine");
        wahlpflichtmodul.setLearningOutcomes("Die Wahlfachangebote erlauben es den Studierenden, ihre persönlichen Neigungen zu vertiefen und das persönliche Lernportfolio entweder um bisher unbekannte Themen aus der Wirtschaftsinformatik oder um Inhalte aus angrenzenden Fachdisziplinen zu erweitern.");
        wahlpflichtmodul.setContents("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul.setTeachingMethodology("Abhängig von der gewählten Veranstaltung.");
        wahlpflichtmodul.setReadingList("Abhängig von der gewählten Veranstaltung.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        wahlpflichtmodul.setAreaOfStudies(wahl);

        return wahlpflichtmodul;
    }

    private Module createModuleVerteilteSysteme() {
        Module verteilteSysteme = new Module();
        verteilteSysteme.setTitle("Verteilte Systeme");
        verteilteSysteme.setCode("WIB56");
        verteilteSysteme.setSubtitle("");
        verteilteSysteme.setOfferFrequency("jedes Semester");
        verteilteSysteme.setModuleCoordinator("Prof. Dr. Eckhard Ammann");
        verteilteSysteme.setLecturers("Prof. Dr. Eckhard Ammann");
        verteilteSysteme.setTeachingLanguage("Deutsch");
        verteilteSysteme.setSemester(5);
        verteilteSysteme.setCredits(5);
        verteilteSysteme.setPrerequisites("Keine");
        verteilteSysteme.setRecommendedPrerequisites("Fortgeschrittene Programmierung");
        verteilteSysteme.setLearningOutcomes("Die Studierenden entwickeln Verständnis für das Zusammenwirken von Anwendungen und Programmteilen in verteilten Umgebungen, insbesondere auch die dabei auftretenden neuen Problemstellungen relativ zu nicht verteilten Anwendungen.");
        verteilteSysteme.setContents("Konzepte, Modelle und Problemstellungen von verteilten Systemen und Anwendungen werden eingeführt.");
        verteilteSysteme.setTeachingMethodology("Vorlesung: Vortrag und Referate Praktikum/Übungen: Entwickeln von verteilten Anwendungslösungen, Lösen von Programmieraufgaben");
        verteilteSysteme.setReadingList("A.Tanenbaum, M.van Steen: Distributed Systems – Principles and Paradigms, Pearson Prentice Hall (2007)");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        verteilteSysteme.setAreaOfStudies(informatik);

        return verteilteSysteme;
    }

    private Module createModuleSystemeUndSicherheit() {
        Module systemeUndSicherheit = new Module();
        systemeUndSicherheit.setTitle("Systeme und Sicherheit");
        systemeUndSicherheit.setCode("WIB54");
        systemeUndSicherheit.setSubtitle("");
        systemeUndSicherheit.setOfferFrequency("jedes Semester");
        systemeUndSicherheit.setModuleCoordinator("Prof. Dr. Wolfgang Blochinger");
        systemeUndSicherheit.setLecturers("Prof. Dr. Wolfgang Blochinger");
        systemeUndSicherheit.setTeachingLanguage("Deutsch");
        systemeUndSicherheit.setSemester(5);
        systemeUndSicherheit.setCredits(5);
        systemeUndSicherheit.setPrerequisites("Keine");
        systemeUndSicherheit.setRecommendedPrerequisites("keine");
        systemeUndSicherheit.setLearningOutcomes("Dieses Modul soll Studierenden sowohl im Bereich der Systemkonzepte als auch im Bereich der ITSicherheit jeweils umfassende Kenntnisse vermitteln.");
        systemeUndSicherheit.setContents("Aufgaben, Aufbau und grundlegende Funktionsweise von Systemsoftware");
        systemeUndSicherheit.setTeachingMethodology("Folien, Tafel, Demonstrationen und Übungen am Rechner");
        systemeUndSicherheit.setReadingList("Eckert, Claudia (2014): IT-Sicherheit. Konzepte - Verfahren - Protokolle. 9. Aufl. München: Oldenbourg.");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        systemeUndSicherheit.setAreaOfStudies(informatik);

        return systemeUndSicherheit;
    }

    private Module createModuleBusinessConsulting() {
        Module businessConsulting = new Module();
        businessConsulting.setTitle("Business Consulting");
        businessConsulting.setCode("WIB53");
        businessConsulting.setSubtitle("");
        businessConsulting.setOfferFrequency("jedes Semester");
        businessConsulting.setModuleCoordinator("Prof. Dr. N.N");
        businessConsulting.setLecturers("Prof. Dr. N.N");
        businessConsulting.setTeachingLanguage("Englisch");
        businessConsulting.setSemester(5);
        businessConsulting.setCredits(5);
        businessConsulting.setPrerequisites("Keine");
        businessConsulting.setRecommendedPrerequisites("keine");
        businessConsulting.setLearningOutcomes("Die Teilnehmer kennen grundlegende Methoden des Business Consultings, sowie der Business Analysis und können diese Methoden in konkreten Beratungssituationen anwenden.");
        businessConsulting.setContents("Allgemeine Einordnung von Consulting Methoden.");
        businessConsulting.setTeachingMethodology("Vorlesung Übungen und Fallstudien");
        businessConsulting.setReadingList("Schmelzer, H. Sesselmann W. (2013): Geschäftsprozess-Management in der Praxis. 8., korrigierte Auflage. Hanser Verlag.");

        AreaOfStudies methoden = areaOfStudiesJpaRepository.findByName("Methoden");
        businessConsulting.setAreaOfStudies(methoden);

        return businessConsulting;
    }

    private Module createModuleManagementUndControlling() {
        Module managementUndControlling = new Module();
        managementUndControlling.setTitle("Management und Controlling");
        managementUndControlling.setCode("WIB52");
        managementUndControlling.setSubtitle("");
        managementUndControlling.setOfferFrequency("jedes Semester");
        managementUndControlling.setModuleCoordinator("Prof. Dipl.-Kfm. Armin Roth");
        managementUndControlling.setLecturers("Prof. Dipl.-Kfm. Armin Roth");
        managementUndControlling.setTeachingLanguage("Deutsch");
        managementUndControlling.setSemester(5);
        managementUndControlling.setCredits(5);
        managementUndControlling.setPrerequisites("Keine");
        managementUndControlling.setRecommendedPrerequisites("Grundlagen der BWL; Betriebliches Rechnungswesen");
        managementUndControlling.setLearningOutcomes("Innerhalb des Moduls wird der Managementprozess ganzheitlich vorgestellt und diskutiert.");
        managementUndControlling.setContents("Anhand des Management-Prozesses (Planung, Organisation, Personaleinsatz, Führung und Kontrolle) werden die jeweiligen Prozessschritte betrachtet und die jeweils aktuellen Instrumente und Methoden vorgestellt.");
        managementUndControlling.setTeachingMethodology("Vorlesung, Kleingruppenarbeit, Fallstudien, Präsentation und schriftliche Ausarbeitung, Planspiel.");
        managementUndControlling.setReadingList("Staehle, W.: Management, Vahlen, jeweils neueste Auflage.");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        managementUndControlling.setAreaOfStudies(bwl);

        return managementUndControlling;
    }

    private Module createModuleCorporateFinance() {
        Module corporateFinance = new Module();
        corporateFinance.setTitle("Corporate Finance");
        corporateFinance.setCode("WIB51");
        corporateFinance.setSubtitle("");
        corporateFinance.setOfferFrequency("jedes Semester");
        corporateFinance.setModuleCoordinator("Prof. Dr. Josef Schürle");
        corporateFinance.setLecturers("Prof. Dr. Josef Schürle");
        corporateFinance.setTeachingLanguage("Deutsch");
        corporateFinance.setSemester(5);
        corporateFinance.setCredits(5);
        corporateFinance.setPrerequisites("Keine");
        corporateFinance.setRecommendedPrerequisites("Grundlagen der BWL; Betriebliches Rechnungswesen");
        corporateFinance.setLearningOutcomes("Ziel des Moduls ist es, den Studierenden eine Vertiefung in das Themengebiet der betrieblichen Finanzwirtschaft zu geben.");
        corporateFinance.setContents("Grundlagen der Finanzmathematik (Zins-, Renten- und Tilgungsrechnung)");
        corporateFinance.setTeachingMethodology("Vermittlung der theoretischen Grundlagen mittels Beamer-Präsentation, ergänzt durch Tafelanschriebe.");
        corporateFinance.setReadingList("Becker (2015): Investition und Finanzierung: Grundlagen der betrieblichen Finanzwirtschaft. 7. Auflage. Wiesbaden: SpringerGabler.");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        corporateFinance.setAreaOfStudies(bwl);

        return corporateFinance;
    }

    private void createSemester4Wib(ExReg wib) {
        Module berufspraktischesSemester = this.createModuleBerufspraktischesSemester();
        berufspraktischesSemester.setExReg(wib);
        moduleJpaRepository.save(berufspraktischesSemester);

    }

    private Module createModuleBerufspraktischesSemester() {
        Module berufspraktischesSemester = new Module();
        berufspraktischesSemester.setTitle("Berufspraktisches Semester");
        berufspraktischesSemester.setCode("WIB41");
        berufspraktischesSemester.setSubtitle("");
        berufspraktischesSemester.setOfferFrequency("jedes Semester");
        berufspraktischesSemester.setModuleCoordinator("Prof. Dr. Alexander Rossmann");
        berufspraktischesSemester.setLecturers("Prof. Dr. Alexander Rossmann");
        berufspraktischesSemester.setTeachingLanguage("Deutsch");
        berufspraktischesSemester.setSemester(4);
        berufspraktischesSemester.setCredits(30);
        berufspraktischesSemester.setPrerequisites("Bestandene Zwischenprüfung");
        berufspraktischesSemester.setRecommendedPrerequisites("alle Module der Semester 1-3");
        berufspraktischesSemester.setLearningOutcomes("Die Praxisphase des Studienganges Wirtschaftsinformatik dient der Vermittlung praktischer Kenntnisse und der Einübung von Schlüsselqualifikationen.");
        berufspraktischesSemester.setContents("");
        berufspraktischesSemester.setTeachingMethodology("");
        berufspraktischesSemester.setReadingList("Vorbereitungsveranstaltungen zu Form und Inhalt der betrieblichen Praxisphase: Verweise auf Internet-Quellen.");

        AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
        berufspraktischesSemester.setAreaOfStudies(wahl);

        return berufspraktischesSemester;
    }

    private void createSemester3Wib(ExReg wib) {
        Module logistikUndProduktion = this.createModuleLogistikUndProduktion();
        logistikUndProduktion.setExReg(wib);
        moduleJpaRepository.save(logistikUndProduktion);

        Module rhethorikUndKommunikationsverhalten = this.createModuleRhethorikUndKommunikationsverhalten();
        rhethorikUndKommunikationsverhalten.setExReg(wib);
        moduleJpaRepository.save(rhethorikUndKommunikationsverhalten);

        Module softwareengineering = this.createModuleSoftwareengineeringg();
        softwareengineering.setExReg(wib);
        moduleJpaRepository.save(softwareengineering);

        Module relationaleDatenbanken = this.createModuleRelationaleDatenbanken();
        relationaleDatenbanken.setExReg(wib);
        moduleJpaRepository.save(relationaleDatenbanken);

        Module datenbankenPraktikum = this.createModuleDatenbankenPraktikum();
        datenbankenPraktikum.setExReg(wib);
        moduleJpaRepository.save(datenbankenPraktikum);

        Module webprogrammierung = this.createModuleWebprogrammierung();
        webprogrammierung.setExReg(wib);
        moduleJpaRepository.save(webprogrammierung);

    }

    private Module createModuleWebprogrammierung() {
        Module webprogrammierung = new Module();
        webprogrammierung.setTitle("Web-Programmierung");
        webprogrammierung.setCode("WIB36");
        webprogrammierung.setSubtitle("");
        webprogrammierung.setOfferFrequency("jedes Semester");
        webprogrammierung.setModuleCoordinator("Prof. Dr. Martin Schmollinger");
        webprogrammierung.setLecturers("Matthias Gutbrod");
        webprogrammierung.setTeachingLanguage("Deutsch");
        webprogrammierung.setSemester(3);
        webprogrammierung.setCredits(5);
        webprogrammierung.setPrerequisites("Keine");
        webprogrammierung.setRecommendedPrerequisites("Grundlagen der Informatik, Fortgeschrittene Programmierung");
        webprogrammierung.setLearningOutcomes("Das Ziel des Moduls ist es, den Studierenden eine Einführung in die Technologien, den Aufbau und die Programmierung von Webanwendungen zu geben.");
        webprogrammierung.setContents("Das Modul vermittelt grundlegende Technologien von Webanwendungen. Inhalte der Veranstaltung sind die Basistechnologien und ausgewählte Varianten der client- und serverseitigen Programmierung von Webanwendungen (Z.B. JavaScript, Java, PHP). Des Weiteren werden ausgewählte Grundlagen der Absicherung von Webanwendungen vorgestellt ");
        webprogrammierung.setTeachingMethodology("Das Modul besteht aus einer Vorlesung in seminaristischem Stil mit Tafelanschrieb, Tageslichtprojektion und PC-Projektion, sowie einem Praktikum zur Einübung der Vorlesungsinhalte.");
        webprogrammierung.setReadingList("Ackermann, Philip (2016). JavaScript: Das umfassende Handbuch für Einsteiger, Fortgeschrittene und Profis. Rheinwerk Computing.");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        webprogrammierung.setAreaOfStudies(informatik);

        return webprogrammierung;
    }

    private Module createModuleDatenbankenPraktikum() {
        Module datenbankenPraktikum = new Module();
        datenbankenPraktikum.setTitle("Datenbanken Praktikum");
        datenbankenPraktikum.setCode("WIB35");
        datenbankenPraktikum.setSubtitle("");
        datenbankenPraktikum.setOfferFrequency("jedes Semester");
        datenbankenPraktikum.setModuleCoordinator("Prof. Dr. Ilja Petrov");
        datenbankenPraktikum.setLecturers("Prof. Dr. Ilja Petrov");
        datenbankenPraktikum.setTeachingLanguage("Deutsch");
        datenbankenPraktikum.setSemester(3);
        datenbankenPraktikum.setCredits(5);
        datenbankenPraktikum.setPrerequisites("Keine");
        datenbankenPraktikum.setRecommendedPrerequisites("Statistik, Diskrete Mathematik, Grundlagen der Informatik, Fortgeschrittenen Programmierung, Algorithmen und Datenstrukturen");
        datenbankenPraktikum.setLearningOutcomes("Das Ziel des Moduls ist es, den Studierenden eine allgemeine praktische Übung in das Themengebiet der relationalen Datenbanksysteme zu geben.");
        datenbankenPraktikum.setContents("ANSI/SPARC, Entity-Relationship Modell, objektorientierte Konzepte und Modellierung, relationales Modell, Normalisierung, Semantik von Beziehungen, praktische Entwurfsregeln, SQL (Structured Query Language), Transaktionskonzepte, Serialisierbarkeit, Zwei-Phasen-Sperrprotokoll, Datensicherungsund Wiederherstellungsmaßnahmen, Abfrageoptimierung, Programmierschnittstellen (JDBC, Embedded SQL)");
        datenbankenPraktikum.setTeachingMethodology("Seminaristischer Unterricht, Tafel, PPT-Vortrag, Demos, Übungsaufgaben im Praktikum, Skript mit PPT-Folien, Übungsaufgaben, SQL-Lernprogramm ");
        datenbankenPraktikum.setReadingList("Ramez Elmasri, Shamkant Navathe. Grundlagen von Datenbanksystemen, Bachelorausgabe. 3. Auflage. Pearson Studium, 2009, ISBN: 978-3-8689-4012-1");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        datenbankenPraktikum.setAreaOfStudies(informatik);

        return datenbankenPraktikum;
    }

    private Module createModuleRelationaleDatenbanken() {
        Module relationaleDatenbanken = new Module();
        relationaleDatenbanken.setTitle("Relationale Datenbanken");
        relationaleDatenbanken.setCode("WIB34");
        relationaleDatenbanken.setSubtitle("");
        relationaleDatenbanken.setOfferFrequency("jedes Semester");
        relationaleDatenbanken.setModuleCoordinator("Prof. Dr. Ilja Petrov");
        relationaleDatenbanken.setLecturers("Prof. Dr. Ilja Petrov");
        relationaleDatenbanken.setTeachingLanguage("Deutsch");
        relationaleDatenbanken.setSemester(3);
        relationaleDatenbanken.setCredits(5);
        relationaleDatenbanken.setPrerequisites("Keine");
        relationaleDatenbanken.setRecommendedPrerequisites("Grundlagen der Informatik, Statistik, Diskrete Mathematik, Fortgeschrittenen Programmierung, Algorithmen und Datenstrukturen");
        relationaleDatenbanken.setLearningOutcomes("Das Ziel des Moduls ist es, den Studierenden eine allgemeine Einführung in das Themengebiet Datenbank- und Informationssystemen zu geben.");
        relationaleDatenbanken.setContents("ANSI/SPARC, Entity-Relationship Modell, objektorientierte Konzepte und Modellierung, relationales Modell, Normalisierung, Semantik von Beziehungen, praktische Entwurfsregeln, SQL (Structured Query Language), Transaktionskonzepte, Serialisierbarkeit, Zwei-Phasen-Sperrprotokoll, Datensicherungsund Wiederherstellungsmaßnahmen, Abfrageoptimierung, Programmierschnittstellen (JDBC).");
        relationaleDatenbanken.setTeachingMethodology("Seminaristischer Unterricht, Tafel, PPT-Vortrag, Demos, Übungsaufgaben im Praktikum, Skript mit PPT-Folien, Übungsaufgaben, SQL-Lernprogramm");
        relationaleDatenbanken.setReadingList("Ramez Elmasri, Shamkant Navathe. Grundlagen von Datenbanksystemen, Bachelorausgabe. 3. Auflage. Pearson Studium, 2009, ISBN: 978-3-8689-4012-1");

        AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
        relationaleDatenbanken.setAreaOfStudies(informatik);

        return relationaleDatenbanken;
    }

    private Module createModuleSoftwareengineeringg() {
        Module softwareengineering = new Module();
        softwareengineering.setTitle("Software - Engineering");
        softwareengineering.setCode("WIB33");
        softwareengineering.setSubtitle("");
        softwareengineering.setOfferFrequency("jedes Semester");
        softwareengineering.setModuleCoordinator("Prof. Dr. Christian Decker");
        softwareengineering.setLecturers("Prof. Dr. Christian Decker");
        softwareengineering.setTeachingLanguage("Deutsch");
        softwareengineering.setSemester(3);
        softwareengineering.setCredits(5);
        softwareengineering.setPrerequisites("Keine");
        softwareengineering.setRecommendedPrerequisites("Keine");
        softwareengineering.setLearningOutcomes("Softwareprozesse: Grundlegendes Verständnis von Softwareprozessen und Vorgehensmodellen. Wissen, wann kennengelernte Vorgehensmodelle angewendet werden können. Kennen der grundlegenden Prozessaktivitäten");
        softwareengineering.setContents("Software stellt einen immer größeren Faktor der Wertschöpfung von Produkten dar. Softwareintensive Produkte werden weiter zunehmen.");
        softwareengineering.setTeachingMethodology("PDF der Folien aus der Vorlesung. Weiteres Material wird während der Vorlesung bekannt gegeben.");
        softwareengineering.setReadingList("Ian Sommerville. Software Engineering (9. aktualisierte Auflage). Pearson, Deutschland, ISBN: 978-3-8632-6512-0");

        AreaOfStudies methoden = areaOfStudiesJpaRepository.findByName("Methoden");
        softwareengineering.setAreaOfStudies(methoden);

        return softwareengineering;
    }

    private Module createModuleRhethorikUndKommunikationsverhalten() {
        Module rhethorikUndKommunikationsverhalten = new Module();
        rhethorikUndKommunikationsverhalten.setTitle("Rhetorik und Kommunikationsverhalten");
        rhethorikUndKommunikationsverhalten.setCode("WIB32");
        rhethorikUndKommunikationsverhalten.setSubtitle("");
        rhethorikUndKommunikationsverhalten.setOfferFrequency("jedes Semester");
        rhethorikUndKommunikationsverhalten.setModuleCoordinator("Prof. Dipl.-Kfm. Armin Roth");
        rhethorikUndKommunikationsverhalten.setLecturers("Prof. Dipl.-Kfm. Armin Roth");
        rhethorikUndKommunikationsverhalten.setTeachingLanguage("Deutsch");
        rhethorikUndKommunikationsverhalten.setSemester(3);
        rhethorikUndKommunikationsverhalten.setCredits(5);
        rhethorikUndKommunikationsverhalten.setPrerequisites("Keine");
        rhethorikUndKommunikationsverhalten.setRecommendedPrerequisites("keine");
        rhethorikUndKommunikationsverhalten.setLearningOutcomes("Ziel des Moduls ist es, die Studierenden für die Wichtigkeit des Themas Kommunikation und Rhetorik zu sensibilisieren.");
        rhethorikUndKommunikationsverhalten.setContents("In diesem Modul sollen Grundlagen der Kommunikation und der Rhetorik vermittelt werden. Präsentations- und Gesprächsführungstechniken werden behandelt. Außerdem wird in die Moderationstechnik eingeführt.");
        rhethorikUndKommunikationsverhalten.setTeachingMethodology("Seminar, Gruppen- und Einzelarbeit, Rollenspiele, Vorlesungsfolien, Präsentations- und Moderationsmaterial");
        rhethorikUndKommunikationsverhalten.setReadingList("Lay, R.; Birkenbihl, V.: Kommunikationstraining, mgv-Verlag, neueste Aufl.");

        AreaOfStudies methoden = areaOfStudiesJpaRepository.findByName("Methoden");
        rhethorikUndKommunikationsverhalten.setAreaOfStudies(methoden);

        return rhethorikUndKommunikationsverhalten;
    }

    private Module createModuleLogistikUndProduktion() {
        Module logistikUndProduktion = new Module();
        logistikUndProduktion.setTitle("Logistik und Produktion - Industrie 4.0");
        logistikUndProduktion.setCode("WIB31");
        logistikUndProduktion.setSubtitle("");
        logistikUndProduktion.setOfferFrequency("jedes Semester");
        logistikUndProduktion.setModuleCoordinator("Prof. Dr. Herbert Glöckle, Prof. Dr. Dietmar Bönke");
        logistikUndProduktion.setLecturers("Prof. Dr. Herbert Glöckle, Prof. Dr. Dietmar Bönke");
        logistikUndProduktion.setTeachingLanguage("Deutsch");
        logistikUndProduktion.setSemester(3);
        logistikUndProduktion.setCredits(5);
        logistikUndProduktion.setPrerequisites("Keine");
        logistikUndProduktion.setRecommendedPrerequisites("Keine");
        logistikUndProduktion.setLearningOutcomes("Die Studierenden können Planungs- und Herstellungsvorgänge in verschiedenen Produktionsumgebungen und -typologien verstehen und selbständig gestalten. Sie sind in der Lage die zugehörigen Geschäftsprozesse zu entwerfen und davon abgeleitet die IT-Systeme zur Unterstützung dieser Prozesse zu konzipieren.");
        logistikUndProduktion.setContents("Methodische Grundlagen zur Beschreibung von Geschäftsprozessen, Grundlegende Planungs- undRealisierungsaufgaben in der Produktion, Grundlagen der Produktion mit betriebswirtschaftlichen Planungsmodellen und Betriebstypologien, Systeme zur Produktionsplanung und –steuerung, Grundstrukturen, Datenmodelle und Funktionen sowie Lösungsarchitekturen zur Umsetzung vonUnternehmenszielen, Planung und Überwachung von Fertigungsabläufen,Produktionsprogrammplanung, Zeitwirtschaft, Durchlaufterminierung, Kapazitätsplanung, Fertigungssteuerung, Feinterminierung, Fortschrittskontrolle, Betriebsdatenerfassung undAbfallwirtschaft, Entwicklung von der Produktion nach Programm über die Auftragsfertigung zur Dienstleistungsproduktion, Stammdaten, wie Stücklisten und Variantenstücklisten, Kundenauftragund Prognosemodelle, Bedarfsermittlung (MRP), Disposition von Kuppelprodukten, Bestellpunktverfahren, Kanban, OPT, Bestandsführung und Inventur, Einkaufsabwicklung undRechnungsprüfung. Diskussion der Schnittstellen zur Finanzbuchhaltung. Eigenschaften von Dienstleistungen, wie Immaterialität, Nicht-Lagerfähigkeit und ausgeprägter Kundenbezug, undderen Auswirkungen auf betriebswirtschaftliche und informationstechnische Aspekte, Dienstleistungsmarketing und Qualitätsmanagement. Ideen und Realisierungsansätze von Industrie 4.0-Konzepten.");
        logistikUndProduktion.setTeachingMethodology("Vorlesungen mit Fallbeispielen zur Erlernung zielorientierter Lösungsfindung. Werksbesichtigung, Integrativer Workshop mit der Vorlesung Datenbanken. Skripte, beispielhafteAnwendungssoftware, mit der die Teilnehmer selbständig typische Lösungsansätze imkontextorientierten Zusammenhang entwickeln können.");
        logistikUndProduktion.setReadingList("Kürble, Peter , Helmold, Marc , Bode, Olaf H. (2016): Beschaffung, Produktion, Marketing, Marburg: Tectum-Verlag (2016) ");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        logistikUndProduktion.setAreaOfStudies(bwl);

        return logistikUndProduktion;
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
        grundlagenInformatik.setLecturers(
                "Prof. Dr. Eckhard Ammann, " + "Prof. Dr. Wolfgang Blochinger, " + "Prof. Dr. Christian Decker, "
                        + "Prof. Dr. Bernhard Mößner, " + "Prof. Dr. Jürgen Münch, " + " Prof. Dr. Ilia Petrov, "
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
                        + "sowie ausgewählte Aspekte der Unternehmensführung.\n\n" + "Fertigkeiten:\n"
                        + "Die  Studierenden  wenden  das  theoretische  Fachwissen  auf  konkrete  betriebswirtschaftliche \n"
                        + "Fragestellungen  an.  Sie  sind  in  der  Lage,  quantitative  Ergebnisse  abzuleiten,  anhand  derer\n"
                        + "Entscheidungsalternativen zu beurteilen und da\n"
                        + "raus Entscheidungsvorschläge abzuleiten.\n" + "Kompetenzen:\n"
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
        grundlagenBWL.setReadingList(
                "Jung (2013): Allgemeine Betriebswirtschaftslehre. 13. Auflage. Berlin: De Gruyter.\n"
                        + "Wöhe (2016): Einführung in die Allgemeine Betriebswirtschaftslehre. 26. Auflage. München: Vahlen\n"
                        + "Wöhe / Kaiser / Döring (2016): Übungsbuch zur allgemeinen Betriebswirtschaftslehre. 15. Auflage. München: Vahlen");

        AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
        grundlagenBWL.setAreaOfStudies(bwl);

        return grundlagenBWL;
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
            Module demo1 = new Module();
            demo1.setTitle("Grundlagen der Betriebswirtschaftslehre");
            demo1.setCode("WIB11");
            demo1.setSubtitle("");
            demo1.setOfferFrequency("jedes Semester");
            demo1.setModuleCoordinator("Prof. Dr. Josef Schürle");
            demo1.setLecturers("Prof. Dr. Josef Schürle");
            demo1.setTeachingLanguage("Deutsch");
            demo1.setSemester(1);
            demo1.setCredits(5);
            demo1.setPrerequisites("Keine");
            demo1.setRecommendedPrerequisites("Keine");
            AreaOfStudies bwl = areaOfStudiesJpaRepository.findByName("BWL");
            demo1.setAreaOfStudies(bwl);
            modules.add(demo1);
            Module demo2 = new Module();
            demo2.setTitle("Statistik");
            demo2.setCode("WIB13");
            demo2.setSubtitle("");
            demo2.setOfferFrequency("jedes Semester");
            demo2.setModuleCoordinator("Prof. Dr. Christian Decker");
            demo2.setLecturers("Prof. Dr. Christian Decker");
            demo2.setTeachingLanguage("Deutsch");
            demo2.setSemester(1);
            demo2.setCredits(5);
            demo2.setPrerequisites("Online Testat bestanden");
            demo2.setRecommendedPrerequisites("Keine");
            AreaOfStudies mathe = areaOfStudiesJpaRepository.findByName("Mathematik");
            demo2.setAreaOfStudies(mathe);
            modules.add(demo2);
            Module demo3 = new Module();
            demo3.setTitle("Fortgeschrittene Programmierung");
            demo3.setCode("WIB26");
            demo3.setSubtitle("");
            demo3.setOfferFrequency("jedes Semester");
            demo3.setModuleCoordinator("Prof. Dr. Martin Schmollinger");
            demo3.setLecturers("Prof. Dr. Martin Schmollinger");
            demo3.setTeachingLanguage("Deutsch");
            demo3.setSemester(2);
            demo3.setCredits(5);
            demo3.setPrerequisites("Keine");
            demo3.setRecommendedPrerequisites("Grundlagen der Informatik");
            AreaOfStudies informatik = areaOfStudiesJpaRepository.findByName("Informatik");
            demo3.setAreaOfStudies(informatik);
            modules.add(demo3);
            Module demo4 = new Module();
            demo4.setTitle("Software - Engineering");
            demo4.setCode("WIB33");
            demo4.setSubtitle("");
            demo4.setOfferFrequency("jedes Semester");
            demo4.setModuleCoordinator("Prof. Dr. Christian Decker");
            demo4.setLecturers("Prof. Dr. Christian Decker");
            demo4.setTeachingLanguage("Deutsch");
            demo4.setSemester(3);
            demo4.setCredits(5);
            demo4.setPrerequisites("Keine");
            demo4.setRecommendedPrerequisites("Keine");
            AreaOfStudies methoden = areaOfStudiesJpaRepository.findByName("Methoden");
            demo4.setAreaOfStudies(methoden);
            modules.add(demo4);
            Module demo5 = new Module();
            demo5.setTitle("Berufspraktisches Semester");
            demo5.setCode("WIB41");
            demo5.setSubtitle("");
            demo5.setOfferFrequency("jedes Semester");
            demo5.setModuleCoordinator("Prof. Dr. Alexander Rossmann");
            demo5.setLecturers("Prof. Dr. Alexander Rossmann");
            demo5.setTeachingLanguage("Deutsch");
            demo5.setSemester(4);
            demo5.setCredits(30);
            demo5.setPrerequisites("Bestandene Zwischenprüfung");
            demo5.setRecommendedPrerequisites("alle Module der Semester 1-3");
            AreaOfStudies wahl = areaOfStudiesJpaRepository.findByName("Wahl/Projekt/Thesis");
            demo5.setAreaOfStudies(wahl);
            modules.add(demo5);

            moduleJpaRepository.saveAll(modules);
        }
    }

    @SuppressWarnings("checkstyle:magicnumber")
    private void createAreaOfStudies() {
        List<AreaOfStudies> areaOfStudies = areaOfStudiesJpaRepository.findAll();
        if (areaOfStudies.isEmpty()) {
            AreaOfStudies informatik = new AreaOfStudies();
            informatik.setName("Informatik");
            informatik.setColorRGB("253,235,16");
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

            AreaOfStudies wahl = new AreaOfStudies();
            wahl.setName("Wahl/Projekt/Thesis");
            wahl.setColorRGB("0,176,80");
            areaOfStudiesJpaRepository.save(wahl);
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
