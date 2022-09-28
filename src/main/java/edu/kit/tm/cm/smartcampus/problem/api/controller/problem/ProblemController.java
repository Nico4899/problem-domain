package edu.kit.tm.cm.smartcampus.problem.api.controller.problem;

import edu.kit.tm.cm.smartcampus.problem.api.configuration.error.ServerExceptionInterceptor;
import edu.kit.tm.cm.smartcampus.problem.api.controller.ProblemOperations;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the server controller for this domain service. It holds a Spring
 * {@link Bean} of {@link Service} managing all logical operations and running domain constraint
 * validators. It sends REST-Server responses in JSON format via the Spring internal
 * {@link RestController} annotation. In case of errors the {@link ServerExceptionInterceptor}
 * returns given information as REST error response.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@RestController
public class ProblemController implements ProblemOperations {

  private final Service service;

  /**
   * Instantiates a new Server controller for the problem domain service, it implements all
   * {@link ProblemOperations}.
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
  public Problem createProblem(ServerCreateProblemRequest serverCreateProblemRequest) {
    return service.createProblem(serverCreateProblemRequest);
  }

  @Override
  public Problem getProblem(String pin) {
    return service.getProblem(pin);
  }

  @Override
  public Problem updateProblem(ServerUpdateProblemRequest serverUpdateProblemRequest) {
    return service.updateProblem(serverUpdateProblemRequest);
  }

  @Override
  public void removeProblem(String pin) {
    service.removeProblem(pin);
  }

  @Override
  public void removeProblemsByReferenceIdentificationNumber(String rin) {
    service.removeProblemsByReferenceIdentificationNumber(rin);
  }
}
