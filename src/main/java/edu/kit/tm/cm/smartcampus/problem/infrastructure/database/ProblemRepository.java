package edu.kit.tm.cm.smartcampus.problem.infrastructure.database;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository uses the standard implementation of {@link CrudRepository} and contains {@link
 * Problem} entities. Primary keys are here of type {@link String} and have format: 'p-(positive
 * int)'
 */
@Repository
public interface ProblemRepository extends CrudRepository<Problem, String> {}
