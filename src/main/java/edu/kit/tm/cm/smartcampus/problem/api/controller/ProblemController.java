package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.problem.api.operations.ProblemOperations;
import edu.kit.tm.cm.smartcampus.problem.api.requests.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * This class represents the server controller for this domain service. It holds a Spring {@link
 * Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal {@link
 * RestController} annotation. In case of errors the {@link ServerExceptionInterceptor} returns
 * given information as REST error response.
 */
@RestController
public class ProblemController implements ProblemOperations {

  private final Service service;

  /**
   * Instantiates a new Server controller for the problem domain service, it implements all {@link
   * ProblemOperations}.
   *
   * @param service the problem service which controls all domain logic (constructor injected)
   */
  @Autowired
  public ProblemController(Service service) {
    this.service = service;
  }

  @Override
  public Collection<Problem> listProblems() {
    return service.listProblems();
  }

  @Override
  public Problem createProblem(ProblemRequest problemRequest) {
    return service.createProblem(problemRequest);
  }

  @Override
  public Problem getProblem(String pin) {
    return service.getProblem(pin);
  }

  @Override
  public Problem updateProblem(Problem problem) {
    return service.updateProblem(problem);
  }

  @Override
  public void removeProblem(String pin) {
    service.removeProblem(pin);
  }
}
