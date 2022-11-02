package edu.kit.tm.cm.smartcampus.problem.logic.operations.utility;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This Class provides utils for data transfer objects.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataTransferUtils {

  /**
   * This class represents a server request reader.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ServerRequestReader {

    /**
     * Reads a server create request for a problem and converts it to a problem.
     *
     * @param serverCreateProblemRequest the server create request
     * @return the problem
     */
    public static Problem readServerCreateProblemRequest(
        ServerCreateProblemRequest serverCreateProblemRequest) {
      Problem problem = new Problem();
      problem.setTitle(serverCreateProblemRequest.getTitle());
      problem.setDescription(serverCreateProblemRequest.getDescription());
      problem.setReferenceIdentificationNumber(
          serverCreateProblemRequest.getReferenceIdentificationNumber());
      problem.setNotificationIdentificationNumber(
          serverCreateProblemRequest.getNotificationIdentificationNumber());
      problem.setReporter(serverCreateProblemRequest.getReporter());
      return problem;
    }

    /**
     * Reads a server update request for a problem and converts it to a problem.
     *
     * @param serverUpdateProblemRequest the server update request
     * @return the problem
     */
    public static Problem readServerUpdateProblemRequest(
        ServerUpdateProblemRequest serverUpdateProblemRequest) {
      Problem problem = new Problem();
      problem.setTitle(serverUpdateProblemRequest.getTitle());
      problem.setDescription(serverUpdateProblemRequest.getDescription());
      problem.setReferenceIdentificationNumber(
          serverUpdateProblemRequest.getReferenceIdentificationNumber());
      problem.setNotificationIdentificationNumber(
          serverUpdateProblemRequest.getNotificationIdentificationNumber());
      problem.setReporter(serverUpdateProblemRequest.getReporter());
      problem.setState(serverUpdateProblemRequest.getState());
      problem.setIdentificationNumber(serverUpdateProblemRequest.getIdentificationNumber());
      return problem;
    }

  }
}
