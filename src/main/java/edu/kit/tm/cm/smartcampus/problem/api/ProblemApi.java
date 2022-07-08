package edu.kit.tm.cm.smartcampus.problem.api;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    Problem editProblem(Problem problem);

    @DeleteMapping("/problems/{pin}")
    void deleteProblem(@PathVariable String pin);

}
