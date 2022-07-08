package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemResponse;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemsResponse;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemController implements ProblemApi {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @Override
    public ProblemsResponse listProblems() {
        return problemService.getProblems();
    }

    @Override
    public ProblemResponse createProblem(ProblemRequest problemRequest) {
        return problemService.createProblem(problemRequest);
    }

    @Override
    public ProblemResponse getProblem(String pin) {
        return problemService.getProblem(pin);
    }

    @Override
    public ProblemResponse editProblem(String pin, ProblemRequest problemRequest) {
        problemService.editProblem(pin, problemRequest);
        return problemService.getProblem(pin);
    }

    @Override
    public void deleteProblem(String pin) {
        problemService.deleteProblem(pin);
    }
}

