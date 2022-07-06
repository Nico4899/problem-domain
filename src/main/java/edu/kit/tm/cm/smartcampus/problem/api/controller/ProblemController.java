package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemResponse;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemsResponse;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.operations.ProblemOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProblemController implements ProblemApi {

    private final ProblemOperations problemOperations;

    @Autowired
    public ProblemController(ProblemOperations problemOperations) {
        this.problemOperations = problemOperations;
    }

    @Override
    public ProblemsResponse listProblems() {
        return problemOperations.getProblems();
    }

    @Override
    public ProblemResponse createProblem(ProblemRequest problemRequest) {
        return problemOperations.createProblem(problemRequest);
    }

    @Override
    public ProblemResponse getProblem(String pin) {
        return problemOperations.getProblem(pin);
    }

    @Override
    public ProblemResponse editProblem(String pin, ProblemRequest problemRequest) {
        problemOperations.editProblem(pin, problemRequest);
        return problemOperations.getProblem(pin);
    }

    @Override
    public void deleteProblem(String pin) {
        problemOperations.deleteProblem(pin);
    }
}

