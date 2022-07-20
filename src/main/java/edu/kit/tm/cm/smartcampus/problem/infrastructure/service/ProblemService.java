package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.InputValidator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";
  private static final String NIN_PATTERN = "^n-\\d+$";

  private static final String REFERENCE_IN_PATTERN = ""; //TODO!

  private final ProblemRepository problemRepository;

  private final InputValidator inputValidator;

  @Autowired
  public ProblemService(ProblemRepository problemRepository, InputValidator inputValidator) {
    this.problemRepository = problemRepository;
    this.inputValidator = inputValidator;
  }

  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String pin) throws ResourceNotFoundException, InvalidArgumentsException {
    validatePin(pin);

    Optional<Problem> optionalProblem = problemRepository.findById(pin);
    if (optionalProblem.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return optionalProblem.get();
  }

  public Problem createProblem(Problem problem) {
    validateProblem(problem); //TODO hier evtl noch nicht die id prüfen
    return problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    validateProblem(problem);
    if (problemRepository.findById(problem.getPin()).isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return problemRepository.save(problem);
  }

  public void removeProblem(String pin) {
    validatePin(pin);
    if (problemRepository.findById(pin).isEmpty()) {
      throw new ResourceNotFoundException();
    }
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
