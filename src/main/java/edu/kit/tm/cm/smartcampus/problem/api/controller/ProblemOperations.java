package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import java.util.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This interface provides all rest server operations connected to "/problems" requests. It also
 * provides their {@link GetMapping} for the call url.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@RequestMapping("/problems")
public interface ProblemOperations {

  /**
   * List all problems on "/problems" url.
   *
   * @return a collection of all {@link Problem} this domain service manages
   */
  @GetMapping("")
  Collection<Problem> listProblems();

  /**
   * Create a new {@link Problem} on "/problems" url in this domain service.
   *
   * @param serverCreateProblemRequest the request for the problem to be created in this service
   * @return the created problem
   */
  @PostMapping("")
  Problem createProblem(@RequestBody ServerCreateProblemRequest serverCreateProblemRequest);

  /**
   * Update a specific {@link Problem} on "/problems" url in this domain service.
   *
   * @param serverUpdateProblemRequest the problem to be updated in this service
   * @return the updated problem with updated attributes
   */
  @PutMapping("")
  Problem updateProblem(@RequestBody ServerUpdateProblemRequest serverUpdateProblemRequest);

  /**
   * Get a specific {@link Problem} on "/problems/{pin}" url from tis domain service.
   *
   * @param pin identification number of the requested problem in this domain service
   * @return the requested problem
   */
  @GetMapping("/{pin}")
  Problem getProblem(@PathVariable String pin);

  /**
   * Remove a {@link Problem} on "/problems/{pin}" from this domain service.
   *
   * @param pin identification number of the problem to be removed
   */
  @DeleteMapping("/{pin}")
  void removeProblem(@PathVariable String pin);

  /**
   * Remove all {@link Problem} on "/problems" with a certain referenceIdentificationNumber from
   * this domain service.
   *
   * @param rin reference identification number of the problems to be removed
   */
  @DeleteMapping("/{rin}")
  void removeProblemsByReferenceIdentificationNumber(@PathVariable String rin);
}
