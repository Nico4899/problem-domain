package edu.kit.tm.cm.smartcampus.problem.logic.operations.utility;

import edu.kit.tm.cm.smartcampus.problem.api.controller.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataTransferUtils {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ServerRequestReader {

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
      return problem;
    }

  }
}
