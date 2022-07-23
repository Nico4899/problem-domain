package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.ProblemValidator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.Validator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the {@link org.springframework.stereotype.Service} of this domain service,
 * it provides all logic and holds {@link Bean} instances of {@link Validator} and {@link
 * CrudRepository}* to manage incoming requests and control sent requests.
 */
@org.springframework.stereotype.Service
public class Service {

  private final ProblemRepository problemRepository;
  private final ProblemValidator problemValidator;

  /**
   * Constructs a new service instance for this domain service.
   *
   * @param problemRepository repository in which problem entities are stored (constructor injected)
   * @param problemValidator validator which validates various requests (constructor injected)
   */
  @Autowired
  public Service(ProblemRepository problemRepository, ProblemValidator problemValidator) {
    this.problemRepository = problemRepository;
    this.problemValidator = problemValidator;
  }

  /**
   * List all {@link Problem} this service manages.
   *
   * @return all problems of this service
   */
  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : this.problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  /**
   * Gets a specific {@link Problem} this domain manages.
   *
   * @param problemIdentificationNumber the identification number of the requested problem
   * @return the requested problem
   */
  public Problem getProblem(String problemIdentificationNumber) {
    this.problemValidator.validate(problemIdentificationNumber);
    return this.problemRepository.findById(problemIdentificationNumber).get();
  }

  /**
   * Create a new {@link Problem} in this domain service.
   *
   * @param problem the problem to be created
   * @return the created problem
   */
  public Problem createProblem(Problem problem) {
    this.problemValidator.validateCreate(problem);
    return this.problemRepository.save(problem);
  }

  /**
   * Update a {@link Problem} in this domain service.
   *
   * @param problem the problem to be updated
   * @return the updated problem
   */
  public Problem updateProblem(Problem problem) {
    this.problemValidator.validateUpdate(problem);
    return this.problemRepository.save(problem);
  }

  /**
   * Remove a {@link Problem} from this domain service.
   *
   * @param problemIdentificationNumber the identification number of the problem
   */
  public void removeProblem(String problemIdentificationNumber) {
    this.problemValidator.validate(problemIdentificationNumber);
    problemRepository.deleteById(problemIdentificationNumber);
  }
}
