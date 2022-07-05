package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemResponse;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemsResponse;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProblemController implements ProblemApi {

    @Override
    public ProblemsResponse listProblems() {
        return null;
    }

    @Override
    public ProblemResponse createProblem(ProblemRequest problemRequest) {
        return null;
    }

    @Override
    public ProblemResponse getProblem(String pin) {
        return null;
    }

    @Override
    public ProblemResponse editProblem(String pin, ProblemRequest problemRequest) {
        return null;
    }

    @Override
    public void deleteProblem(String pin) {

    }
}

