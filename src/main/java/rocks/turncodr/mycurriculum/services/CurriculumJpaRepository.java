package rocks.turncodr.mycurriculum.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.turncodr.mycurriculum.model.Curriculum;

/**
 * JPARepository for Entity Curriculum.
 */
public interface CurriculumJpaRepository extends JpaRepository<Curriculum, Integer> {
    List<Curriculum> findByName(String name);
    List<Curriculum> findByAcronym(String acronym);
}
