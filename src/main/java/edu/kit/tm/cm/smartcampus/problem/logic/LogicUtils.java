package edu.kit.tm.cm.smartcampus.problem.logic;

import edu.kit.tm.cm.smartcampus.problem.api.requests.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;

public class LogicUtils {

  public static Problem convertProblemRequestToProblem(ProblemRequest problemRequest) {

    Problem problem = new Problem();
    problem.setTitle(problemRequest.getTitle());
    problem.setDescription(problemRequest.getDescription());
    problem.setReferenceIdentificationNumber(problemRequest.getReferenceIdentificationNumber());
    problem.setNotificationIdentificationNumber(problemRequest.getNotificationIdentificationNumber());
    problem.setReporter(problemRequest.getReporter());
    return problem;
  }

}
