package rocks.turncodr.mycurriculum.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.turncodr.mycurriculum.model.ExReg;

/**
 * JpaRepository for Examination Regulations (ExReg).
 */
@Repository
public interface ExRegJpaRepository extends JpaRepository<ExReg, Integer> {
}
