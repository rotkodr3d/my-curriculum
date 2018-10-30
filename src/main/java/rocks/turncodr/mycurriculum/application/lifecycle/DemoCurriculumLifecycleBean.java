package rocks.turncodr.mycurriculum.application.lifecycle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import rocks.turncodr.mycurriculum.model.Curriculum;
import rocks.turncodr.mycurriculum.services.CurriculumJpaRepository;

/**
 * Lifecycle bean that creates demo curricula.
 *
 *
 */
@Component
public class DemoCurriculumLifecycleBean implements SmartLifecycle {

    private boolean running;

    @Autowired
    private CurriculumJpaRepository curriculumService;

    @Override
    public void start() {
        running = true;
        this.createCurriculum();
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
