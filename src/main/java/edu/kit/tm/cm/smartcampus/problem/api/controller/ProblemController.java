package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.NotFoundException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.ProblemService;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProblemController implements ProblemApi {

  private final ProblemService problemService;

  @Autowired
  public ProblemController(ProblemService problemService) {
    this.problemService = problemService;
  }

  @Override
  public Collection<Problem> listProblems() {
    return problemService.getProblems();
  }

  @Override
  public Problem createProblem(Problem problem) {
    return problemService.createProblem(problem);
  }

  @Override
  public Problem getProblem(String pin) throws InvalidArgumentsException, NotFoundException {
      return problemService.getProblem(pin);

  }

  @Override
  public Problem updateProblem(Problem problem) throws NotFoundException {
    return problemService.updateProblem(problem);
  }

  @Override
  public void deleteProblem(String pin) throws NotFoundException {
    problemService.deleteProblem(pin);
  }


}

