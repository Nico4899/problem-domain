package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.InputValidator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.ServiceValidator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static edu.kit.tm.cm.smartcampus.problem.utils.*;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";
  private static final String NIN_PATTERN = "^n-\\d+$";

  private static final String REFERENCE_IN_PATTERN = ""; //TODO!

  private final ProblemRepository problemRepository;

  private final InputValidator inputValidator;

  private final ServiceValidator serviceValidator;

  @Autowired
  public ProblemService(ProblemRepository problemRepository, InputValidator inputValidator,
                        ServiceValidator serviceValidator) {
    this.problemRepository = problemRepository;
    this.inputValidator = inputValidator;
    this.serviceValidator = serviceValidator;
  }

  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String pin) throws ResourceNotFoundException, InvalidArgumentsException {
    validatePin(pin);
    serviceValidator.validateInIsMapped(problemRepository, pin);
    Optional<Problem> optionalProblem = problemRepository.findById(pin);
    if (optionalProblem.isEmpty()) {
      throw new ResourceNotFoundException(String.format(NOT_FOUND, pin));
    }
    return optionalProblem.get();
  }

  public Problem createProblem(Problem problem) {
    validateProblem(problem); //TODO hier evtl noch nicht die id prüfen
    serviceValidator.validateInDoesNotExist(problemRepository, problem.getPin());//TODO @Johannes auch noch referenceId usw pruefen?
    return problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    validateProblem(problem);
    serviceValidator.validateInIsMapped(problemRepository, problem.getPin());
    return problemRepository.save(problem);
  }

  public void removeProblem(String pin) {
    validatePin(pin);
    serviceValidator.validateInIsMapped(problemRepository, pin);
    problemRepository.deleteById(pin);
  }

  /**
   * Validates a given Problem (regarding everything that has nothing to do with the database) using the InputValidator.
   *
   * @param problem the problem to be validated
   */
  private void validateProblem(Problem problem) { //TODO validate creation time
    inputValidator.validateNotNull(Map.of(
            "problem title", problem.getProblemTitle(),
            "problem description", problem.getProblemDescription(),
            "problem identification number", problem.getPin(),
            "problem reference identification number", problem.getReferenceIn(),
            "problem notification identification number", problem.getNin(),
            "problem state", problem.getProblemState(),
            "problem reporter", problem.getProblemReporter(),
            "problem creation time attribute", problem.getCreationTime(),
            "problem creation time", problem.getCreationTime().getTime()));

    inputValidator.validateNotEmpty(Map.of(
            "problem title", problem.getProblemTitle(),
            "problem description", problem.getProblemDescription(),
            "problem reporter", problem.getProblemReporter()));

    inputValidator.validateMatchesRegex(Map.of(
            "problem identification number", Pair.of(problem.getPin(), PIN_PATTERN),
            "problem reference identification number", Pair.of(problem.getReferenceIn(), REFERENCE_IN_PATTERN),
            "problem notification identification number",
            Pair.of(problem.getNin(), NIN_PATTERN)));
  }

  /**
   * Validates a given pin (regarding everything that has nothing to do with the database) using the InputValidator.
   *
   * @param pin the pin to be validated
   */
  private void validatePin(String pin) {
    inputValidator.validateNotNull(Map.of("pin", pin));
    inputValidator.validateMatchesRegex(Map.of("pin", Pair.of(pin, PIN_PATTERN)));
  }

}
