package edu.kit.tm.cm.smartcampus.problem;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;

/**
 * This Class provides test utils.
 */
public final class TestUtils {

  /**
   * Checks whether two problems are equal (except for their identification number, creation time
   * and modification time) or not.
   *
   * @param problem1 the first problem to be checked
   * @param problem2 the second problem to be checked
   * @return whether they are equal or not
   */
  public static boolean problemsAreEqual(Problem problem1, Problem problem2) {
    if (problem1 == null || problem2 == null) {
      return false;
    }
    if (problem1 == problem2) {
      return true;
    }

    if (!problem1.getReferenceIdentificationNumber()
        .equals(problem2.getReferenceIdentificationNumber())) {
      return false;
    }
    if (!problem1.getNotificationIdentificationNumber()
        .equals(problem2.getNotificationIdentificationNumber())) {
      return false;
    }
    if (!problem1.getTitle().equals(problem2.getTitle())) {
      return false;
    }
    if (!problem1.getDescription().equals(problem2.getDescription())) {
      return false;
    }
    if (problem1.getState() != problem2.getState()) {
      return false;
    }
    return problem1.getReporter().equals(problem2.getReporter());
  }

}
