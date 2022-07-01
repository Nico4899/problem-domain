package main.java.edu.kit.tm.cm.smartcampus.problem.api.controller;


import main.java.edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import main.java.edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import main.java.edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProblemController implements ProblemApi {


    @Override
    public Collection<Problem> listProblems() {
        return null;
    }

    @Override
    public Problem createProblem(final ProblemRequest problemRequest) {
        return null;
    }

    @Override
    public Problem getProblem(final String pin) {
        return null;
    }

    @Override
    public Problem editProblem(final String pin, final ProblemRequest problemRequest) {
        return null;
    }

    @Override
    public void deleteProblem(final String pin) {

    }
}

