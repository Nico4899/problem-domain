package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.NotFoundException;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Collection<Problem> getProblems() {
        Collection<Problem> problems = new ArrayList<>();
        for (Problem problem : problemRepository.findAll()) problems.add(problem);
        return problems;
    }

    public Problem getProblem(String pin) throws NotFoundException {

        if (problemRepository.findById(pin).isPresent()) {
            return problemRepository.findById(pin).get();
        }
        throw new NotFoundException();
    }

    public Problem createProblem(Problem problem) {

        return problemRepository.save(problem);

    }

    public Problem updateProblem(String pin, Problem problem) {
        problem.setId(pin);
        return problemRepository.save(problem);

    }

    public void deleteProblem(String pin) {
        problemRepository.deleteById(pin);
    }

}
