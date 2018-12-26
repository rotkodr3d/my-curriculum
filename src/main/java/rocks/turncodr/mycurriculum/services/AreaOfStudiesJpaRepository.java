package rocks.turncodr.mycurriculum.services;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import rocks.turncodr.mycurriculum.model.AreaOfStudies;

/**
 * JPARepository for Entity AreaOfStudies.
 */
public interface AreaOfStudiesJpaRepository extends JpaRepository<AreaOfStudies, Integer> {
    AreaOfStudies findByName(String name);
    List<AreaOfStudies> findByColor(int colorAsInt);
}
