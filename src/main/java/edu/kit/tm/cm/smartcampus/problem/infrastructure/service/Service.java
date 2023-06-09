package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator.ProblemValidator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator.Validator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.operations.utility.DataTransferUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

/**
 * This class represents the {@link org.springframework.stereotype.Service} of this domain service,
 * it provides all logic and holds {@link Bean} instances of {@link Validator} and
 * {@link CrudRepository}* to manage incoming requests and control sent requests.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@org.springframework.stereotype.Service
public class Service {

  private final ProblemRepository problemRepository;
  private final ProblemValidator problemValidator;

  /**
   * Constructs a new service instance for this domain service.
   *
   * @param problemRepository repository in which problem entities are stored (constructor
   *                          injected)
   * @param problemValidator  validator which validates various requests (constructor injected)
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
    for (Problem problem : this.problemRepository.findAll()) {
      problems.add(problem);
    }
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
   * @param serverCreateProblemRequest the problem to be created
   * @return the created problem
   */
  public Problem createProblem(ServerCreateProblemRequest serverCreateProblemRequest) {
    this.problemValidator.validateCreate(serverCreateProblemRequest);
    Problem problem =
        DataTransferUtils.ServerRequestReader.readServerCreateProblemRequest(
            serverCreateProblemRequest);
    problem.setState(Problem.State.OPEN);
    problem.setCreationTime(new Timestamp(System.currentTimeMillis()));
    problem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
    return this.problemRepository.save(problem);
  }

  /**
   * Update a {@link Problem} in this domain service.
   *
   * @param serverUpdateProblemRequest the problem to be updated
   * @return the updated problem
   */
  public Problem updateProblem(ServerUpdateProblemRequest serverUpdateProblemRequest) {
    this.problemValidator.validateUpdate(serverUpdateProblemRequest);
    Problem problem = DataTransferUtils.ServerRequestReader.readServerUpdateProblemRequest(
        serverUpdateProblemRequest);
    problem.setCreationTime(
        this.problemRepository.findById(problem.getIdentificationNumber()).get().getCreationTime());
    problem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
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

  /**
   * Remove all {@link Problem} with a specific referenceIdentificationNumber from this domain
   * service.
   *
   * @param referenceIdentificationNumber the reference identification number of the problems
   */
  public void removeProblemsByReferenceIdentificationNumber(String referenceIdentificationNumber) {
    this.problemValidator.validateReferenceIdentificationNumber(referenceIdentificationNumber);
    problemRepository.deleteByReferenceId(referenceIdentificationNumber);
  }
}
