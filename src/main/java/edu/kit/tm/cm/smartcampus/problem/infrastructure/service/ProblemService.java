package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;


import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.ProblemValidator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.validator.ServiceValidator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ProblemService {

  private static final String PIN_PATTERN = "^p-\\d+$";
  private static final String NIN_PATTERN = "^n-\\d+$";

  private static final String REFERENCE_IN_PATTERN = ""; //TODO! raus mit die viecher

  private final ProblemRepository problemRepository;

  private final ProblemValidator problemValidator;

  private final ServiceValidator serviceValidator;

  @Autowired
  public ProblemService(ProblemRepository problemRepository,
                        ProblemValidator problemValidator,
                        ServiceValidator serviceValidator) {
    this.problemRepository = problemRepository;
    this.problemValidator = problemValidator;
    this.serviceValidator = serviceValidator;
  }

  public Collection<Problem> listProblems() {
    Collection<Problem> problems = new ArrayList<>();
    for (Problem problem : this.problemRepository.findAll()) problems.add(problem);
    return problems;
  }

  public Problem getProblem(String problemIdentificationNumber) {
    this.problemValidator.validate(problemIdentificationNumber);
    return this.problemRepository.findById(problemIdentificationNumber).get();
  }

  public Problem createProblem(Problem problem) {
    this.problemValidator.validateCreate(problem);
    return this.problemRepository.save(problem);
  }

  public Problem updateProblem(Problem problem) {
    this.problemValidator.validateUpdate(problem);
    return this.problemRepository.save(problem);
  }

  public void removeProblem(String problemIdentificationNumber) {
    this.problemValidator.validate(problemIdentificationNumber);
    problemRepository.deleteById(problemIdentificationNumber);
  }

}
