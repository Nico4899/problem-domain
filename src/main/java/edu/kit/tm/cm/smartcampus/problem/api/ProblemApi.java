package edu.kit.tm.cm.smartcampus.problem.api;

import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemResponse;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemsResponse;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
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
    ProblemsResponse listProblems();

    @PostMapping("/problems")
    ProblemResponse createProblem(@RequestBody ProblemRequest problemRequest);

    @GetMapping("/problems/{pin}")
    ProblemResponse getProblem(@PathVariable String pin);

    @PutMapping("/problems/{pin}")
    ProblemResponse editProblem(@PathVariable String pin, @RequestBody ProblemRequest problemRequest);

    @DeleteMapping("/problems/{pin}")
    void deleteProblem(@PathVariable String pin);

}
