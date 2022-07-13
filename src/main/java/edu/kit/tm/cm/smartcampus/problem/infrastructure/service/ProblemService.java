package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.NoSuchElementFoundException;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";

  private final ProblemRepository problemRepository;

  @Autowired
  public ProblemService(ProblemRepository problemRepository) {
    this.problemRepository = problemRepository;
  }

  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String pin) throws NoSuchElementFoundException, InvalidArgumentsException {
    if (!pin.matches(PIN_PATTERN)) {
      throw new InvalidArgumentsException("Problem identification number: ", pin, "should match " + PIN_PATTERN, true);
    }
    Optional<Problem> optionalProblem = problemRepository.findById(pin);
    if (optionalProblem.isEmpty()) {
      throw new NoSuchElementFoundException();
    }
    return optionalProblem.get();
  }

  public Problem createProblem(Problem problem) {
    return problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    if (problemRepository.findById(problem.getId()).isEmpty()) {
      throw new NoSuchElementFoundException();
    }
    return problemRepository.save(problem);
  }

  public void deleteProblem(String pin) {
    if (!pin.matches(PIN_PATTERN)) {
      throw new InvalidArgumentsException("Problem identification number: ", pin, "should match: " + PIN_PATTERN, true);
    }
    if (problemRepository.findById(pin).isEmpty()) {
      throw new NoSuchElementFoundException();
    }
    problemRepository.deleteById(pin);
  }

}
