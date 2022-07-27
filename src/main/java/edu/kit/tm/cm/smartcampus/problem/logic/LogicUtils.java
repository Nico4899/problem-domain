package edu.kit.tm.cm.smartcampus.problem.logic;

import edu.kit.tm.cm.smartcampus.problem.api.requests.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;

/**
 * This class provides utilities to work with problems.
 */
public class LogicUtils {

  /**
   * Converts a ProblemRequest to a Problem and sets all attributes of the problem to the values of
   * the existing attributes of the Request.
   *
   * @param problemRequest the Request to be converted
   * @return the resulting problem
   */
  public static Problem convertProblemRequestToProblem(ProblemRequest problemRequest) {

    Problem problem = new Problem();
    problem.setTitle(problemRequest.getTitle());
    problem.setDescription(problemRequest.getDescription());
    problem.setReferenceIdentificationNumber(problemRequest.getReferenceIdentificationNumber());
    problem.setNotificationIdentificationNumber(
        problemRequest.getNotificationIdentificationNumber());
    problem.setReporter(problemRequest.getReporter());
    return problem;
  }

}
