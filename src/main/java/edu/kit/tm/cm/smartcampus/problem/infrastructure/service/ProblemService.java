package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.api.validator.Validator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.NoSuchElementFoundException;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";
  private static final String NIN_PATTERN = "^n-\\d+$";

  private final ProblemRepository problemRepository;

  private final Validator validator;

  @Autowired
  public ProblemService(ProblemRepository problemRepository, Validator validator) {
    this.problemRepository = problemRepository;
    this.validator = validator;
  }

  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String pin) throws NoSuchElementFoundException, InvalidArgumentsException {
    validatePin(pin);

    Optional<Problem> optionalProblem = problemRepository.findById(pin);
    if (optionalProblem.isEmpty()) {
      throw new NoSuchElementFoundException();
    }
    return optionalProblem.get();
  }

  public Problem createProblem(Problem problem) {
    validateProblem(problem); //TODO hier evtl noch nicht die id prüfen
    return problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    validateProblem(problem);
    if (problemRepository.findById(problem.getIdentificationNumber()).isEmpty()) {
      throw new NoSuchElementFoundException();
    }
    return problemRepository.save(problem);
  }

  public void removeProblem(String pin) {
    validatePin(pin);
    if (problemRepository.findById(pin).isEmpty()) {
      throw new NoSuchElementFoundException();
    }
    problemRepository.deleteById(pin);
  }

  public void validateProblem(Problem problem) { //TODO muss man dann trotzdem in der Signatur @throws stehen haben?
    validator.validateNotNull(List.of(
        Pair.of(problem.getProblemDescription(), "problem description"),
        Pair.of(problem.getProblemReporter(), "problem reporter"),
        Pair.of(problem.getProblemState(), "problem state"),
        Pair.of(problem.getProblemTitle(), "problem title"),
        Pair.of(problem.getIdentificationNumber(), "problem identification number"),
        Pair.of(problem.getCreationTime(), "problem creation time"),
        Pair.of(problem.getNotificationIdentificationNumber(), "problem notification identification number"),
        Pair.of(problem.getReferenceIdentificationNumber(), "problem reference identification number")));
    validator.validateNotEmpty(List.of(
        Pair.of(problem.getProblemDescription(), "problem description"),
        Pair.of(problem.getProblemReporter(), "problem reporter"),
        Pair.of(problem.getProblemTitle(), "problem title")));
    validator.validateMatchesRegex(List.of(
        Pair.of(Pair.of(problem.getIdentificationNumber(), PIN_PATTERN), "problem identification number"),
        Pair.of(Pair.of(problem.getNotificationIdentificationNumber(), NIN_PATTERN),
            "problem notification identification number")
        /*Pair.of(problem.getReferenceIdentificationNumber(), "problem reference identification number")*/));
  }

  public void validatePin(String pin) {
    validator.validateNotNull(List.of(Pair.of(pin, "pin")));
    validator.validateMatchesRegex(List.of(Pair.of(Pair.of(pin, PIN_PATTERN), "pin")));
  }

}
