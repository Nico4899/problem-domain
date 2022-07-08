package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;

import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemResponse;
import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemsResponse;
import edu.kit.tm.cm.smartcampus.problem.logic.LogicUtils;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public ProblemsResponse getProblems() {

        return new ProblemsResponse(problemRepository.findAll());
    }

    public ProblemResponse getProblem(String pin) {
        return new ProblemResponse(problemRepository.findById(pin).get());
    }

    public ProblemResponse createProblem(ProblemRequest problemRequest) {

        Problem problem = LogicUtils.convertProblemRequestToProblem(problemRequest);
        String problemId = problemRepository.create(problem);
        return new ProblemResponse(problemRepository.findById(problemId).get());
    }

    public ProblemResponse editProblem(String pin, ProblemRequest problemRequest) {
        Problem problem = LogicUtils.convertProblemRequestToProblem(problemRequest);
        problem.setId(pin);
        problemRepository.update(problem);
        return new ProblemResponse(problem);

    }

    public void deleteProblem(String pin) {
        problemRepository.delete(pin);
    }

}
