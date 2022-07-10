package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.NotFoundException;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";

  private final ProblemRepository problemRepository;

  @Autowired
  public ProblemService(ProblemRepository problemRepository) {
    this.problemRepository = problemRepository;
  }

  public Collection<Problem> getProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String pin) throws NotFoundException, InvalidArgumentsException {
    if (!pin.matches(PIN_PATTERN)) {
      throw new InvalidArgumentsException("Problem identification number: ", pin, "should match " + PIN_PATTERN, true);
    }
    if (problemRepository.findById(pin).isEmpty()) {
      throw new NotFoundException();
    }
    return problemRepository.findById(pin).get();
  }

  public Problem createProblem(Problem problem) {
    return problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    if (problemRepository.findById(problem.getId()).isEmpty()) {
      throw new NotFoundException();
    }
    return problemRepository.save(problem);
  }

  public void deleteProblem(String pin) {
    if (problemRepository.findById(pin).isEmpty()) {
      throw new NotFoundException();
    }
    problemRepository.deleteById(pin);
  }

}
