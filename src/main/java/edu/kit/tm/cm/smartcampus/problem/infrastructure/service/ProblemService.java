package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.ProblemInputValidator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.ServiceValidator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static edu.kit.tm.cm.smartcampus.problem.utils.NOT_FOUND;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";
  private static final String NIN_PATTERN = "^n-\\d+$";

  private static final String REFERENCE_IN_PATTERN = ""; //TODO!

  private final ProblemRepository problemRepository;

  private final ProblemInputValidator problemInputValidator;

  private final ServiceValidator serviceValidator;

  @Autowired
  public ProblemService(ProblemRepository problemRepository, ProblemInputValidator problemInputValidator,
                        ServiceValidator serviceValidator) {
    this.problemRepository = problemRepository;
    this.problemInputValidator = problemInputValidator;
    this.serviceValidator = serviceValidator;
  }

  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String pin) throws ResourceNotFoundException, InvalidArgumentsException {
    problemInputValidator.validatePin(pin);
    serviceValidator.validateInIsMapped(problemRepository, pin);
    Optional<Problem> optionalProblem = problemRepository.findById(pin);
    if (optionalProblem.isEmpty()) {
      throw new ResourceNotFoundException(String.format(NOT_FOUND, pin));
    }
    return optionalProblem.get();
  }

  public Problem createProblem(Problem problem) {
    problemInputValidator.validateProblem(problem);
    serviceValidator.validateInDoesNotExist(problemRepository, problem.getPin());
    return problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    problemInputValidator.validateProblem(problem);
    serviceValidator.validateInIsMapped(problemRepository, problem.getPin());
    return problemRepository.save(problem);
  }

  public void removeProblem(String pin) {
    problemInputValidator.validatePin(pin);
    serviceValidator.validateInIsMapped(problemRepository, pin);
    problemRepository.deleteById(pin);
  }

}
