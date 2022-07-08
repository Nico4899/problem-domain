package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Collection<Problem> getProblems() {

        return problemRepository.findAll();
    }

    public Problem getProblem(String pin) {
        return problemRepository.findById(pin);
    }

    public Problem createProblem(Problem problem) {

        return problemRepository.create(problem);

    }

    public Problem editProblem(Problem problem) {
        return problemRepository.update(problem);

    }

    public void deleteProblem(String pin) {
        problemRepository.delete(pin);
    }

}
