package edu.kit.tm.cm.smartcampus.problem.api.controller.problem;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This interface provides all rest server operations connected to "/problems" requests. It also
 * provides their {@link GetMapping} for the call url.
 */
@RequestMapping
public interface ProblemOperations {

  /**
   * List all problems on "/problems" url.
   *
   * @return a collection of all {@link Problem} this domain service manages
   */
  @GetMapping("/problems")
  Collection<Problem> listProblems();

  /**
   * Create a new {@link Problem} on "/problems" url in this domain service.
   *
   * @param serverCreateProblemRequest the request for the problem to be created in this service
   * @return the created problem
   */
  @PostMapping("/problems")
  Problem createProblem(@RequestBody ServerCreateProblemRequest serverCreateProblemRequest);

  /**
   * Update a specific {@link Problem} on "/problems" url in this domain service.
   *
   * @param serverUpdateProblemRequest the problem to be updated in this service
   * @return the updated problem with updated attributes
   */
  @PutMapping("/problems")
  Problem updateProblem(@RequestBody ServerUpdateProblemRequest serverUpdateProblemRequest);

  /**
   * Get a specific {@link Problem} on "/problems/{pin}" url from tis domain service.
   *
   * @param pin identification number of the requested problem in this domain service
   * @return the requested problem
   */
  @GetMapping("/problems/{pin}")
  Problem getProblem(@PathVariable String pin);

  /**
   * Remove a {@link Problem} on "/problems/{pin}" from this domain service.
   *
   * @param pin identification number of the problem to be removed
   */
  @DeleteMapping("/problems/{pin}")
  void removeProblem(@PathVariable String pin);
}
