package edu.kit.tm.cm.smartcampus.problem.api;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping
public interface ProblemApi {

  @GetMapping("/problems")
  Collection<Problem> listProblems();

  @PostMapping("/problems")
  Problem createProblem(Problem problem);

  @GetMapping("/problems/{pin}")
  Problem getProblem(@PathVariable String pin);

  @PutMapping("/problems/{pin}")
  Problem updateProblem(Problem problem);

  @DeleteMapping("/problems/{pin}")
  void deleteProblem(@PathVariable String pin);

}
