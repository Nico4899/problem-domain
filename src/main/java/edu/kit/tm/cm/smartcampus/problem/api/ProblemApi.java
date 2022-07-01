package main.java.edu.kit.tm.cm.smartcampus.problem.api;

import main.java.edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import main.java.edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping
public interface ProblemApi {

    @GetMapping("/problems")
    Collection<Problem> listProblems();

    @PostMapping("/problems")
    Problem createProblem(@RequestBody ProblemRequest problemRequest);

    @GetMapping("/problems/{pin}")
    Problem getProblem(@PathVariable String pin);

    @PutMapping("/problems/{pin}")
    Problem editProblem(@PathVariable String pin, @RequestBody ProblemRequest problemRequest);

    @DeleteMapping("/problems/{pin}")
    void deleteProblem(@PathVariable String pin);

}
