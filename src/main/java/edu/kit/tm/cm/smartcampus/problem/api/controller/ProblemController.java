package edu.kit.tm.cm.smartcampus.problem.api.controller;

import edu.kit.tm.cm.smartcampus.problem.api.ProblemApi;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.NotFoundException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.ProblemService;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProblemController implements ProblemApi {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @Override
    public Collection<Problem> listProblems() {
        return problemService.getProblems();
    }

    @Override
    public Problem createProblem(Problem problem) {
        return problemService.createProblem(problem);
    }

    @Override
    public Problem getProblem(String pin) {
        try {
            return problemService.getProblem(pin);
        } catch (NotFoundException e) {
            handleNotFoundException(e);
        }
        return null;
    }

    @Override
    public Problem updateProblem(String pin, Problem problem) {
        return problemService.updateProblem(pin, problem);
    }

    @Override
    public void deleteProblem(String pin) {
        problemService.deleteProblem(pin);
    }

    //@ExceptionHandler(NotFoundException.class)
    //public

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(
            NotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }


}

