package edu.kit.tm.cm.smartcampus.problem.api.operations;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/problems")
public interface ProblemOperations {

  @GetMapping("")
  Collection<Problem> listProblems();

  @PostMapping("")
  Problem createProblem(Problem problem);

  @PutMapping("")
  Problem updateProblem(Problem problem);

  @GetMapping("/{pin}")
  Problem getProblem(@PathVariable String pin);

  @DeleteMapping("/{pin}")
  void removeProblem(@PathVariable String pin);

}
