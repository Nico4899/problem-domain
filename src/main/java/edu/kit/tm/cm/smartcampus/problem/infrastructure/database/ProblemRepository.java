package edu.kit.tm.cm.smartcampus.problem.infrastructure.database;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains
 * {@link Problem} entities. Primary keys are here of type {@link String} and have format:
 * 'p-(positive int)'
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Repository
public interface ProblemRepository extends CrudRepository<Problem, String> {

  @Transactional
  @Modifying
  @Query("DELETE From problem problem Where problem.referenceIdentificationNumber = ?1")
  void deleteByReferenceId(
      @Param("referenceIdentificationNumber") String referenceIdentificationNumber);

}
