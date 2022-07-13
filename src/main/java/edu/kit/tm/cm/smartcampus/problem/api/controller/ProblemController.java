package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.ProblemService;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
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
    return problemService.listProblems();
  }

  @Override
  public Problem createProblem(Problem problem) {
    return problemService.createProblem(problem);
  }

  @Override
  public Problem getProblem(String pin) {
    return problemService.getProblem(pin);
  }

  @Override
  public Problem updateProblem(Problem problem) {
    return problemService.updateProblem(problem);
  }

  @Override
  public void removeProblem(String pin) {
    problemService.removeProblem(pin);
  }


}

