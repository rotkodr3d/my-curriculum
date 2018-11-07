package rocks.turncodr.mycurriculum.services;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.turncodr.mycurriculum.model.ExReg;
import rocks.turncodr.mycurriculum.model.Module;

import java.util.List;

/**
 * JPARepository for Modules.
 */

public interface ModuleJpaRepository extends JpaRepository<Module, Integer>  {
    List<Module> findByExReg(ExReg exReg);
}
