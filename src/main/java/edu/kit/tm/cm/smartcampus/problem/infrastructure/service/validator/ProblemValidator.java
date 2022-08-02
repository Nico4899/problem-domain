package edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating
 * {@link Problem} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class ProblemValidator extends
    Validator<ServerUpdateProblemRequest, ServerCreateProblemRequest> {

  /**
   * Instantiates a new problem validator.
   *
   * @param problemRepository the problem repository in which all problems are saved
   */
  @Autowired
  protected ProblemValidator(ProblemRepository problemRepository) {
    super(problemRepository);
  }

  @Override
  protected String getValidateRegex() {
    return PIN_PATTERN;
  }

  @Override
  public void validateCreate(ServerCreateProblemRequest serverCreateProblemRequest) {
    validateNotNull(
        List.of(
            Pair.of(PROBLEM_REQUEST_NAME, serverCreateProblemRequest),
            Pair.of(TITLE_NAME, serverCreateProblemRequest.getTitle()),
            Pair.of(DESCRIPTION_NAME, serverCreateProblemRequest.getDescription()),
            Pair.of(REFERENCE_IDENTIFICATION_NUMBER_NAME,
                serverCreateProblemRequest.getReferenceIdentificationNumber()),
            Pair.of(NOTIFICATION_IDENTIFICATION_NUMBER_NAME,
                serverCreateProblemRequest.getNotificationIdentificationNumber()),
            Pair.of(REPORTER_NAME, serverCreateProblemRequest.getReporter())));

    validateNotEmpty(
        Map.of(
            TITLE_NAME, serverCreateProblemRequest.getTitle(),
            DESCRIPTION_NAME, serverCreateProblemRequest.getDescription(),
            REPORTER_NAME, serverCreateProblemRequest.getReporter()));

    validateMatchesRegex(
        Map.of(
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
            Pair.of(serverCreateProblemRequest.getReferenceIdentificationNumber(),
                BIN_RIN_CIN_PATTERN),
            NOTIFICATION_IDENTIFICATION_NUMBER_NAME,
            Pair.of(serverCreateProblemRequest.getNotificationIdentificationNumber(),
                NIN_PATTERN)));
  }

  @Override
  public void validateUpdate(ServerUpdateProblemRequest serverUpdateProblemRequest) {
    validateNotNull(List.of(
        Pair.of(PROBLEM_NAME, serverUpdateProblemRequest),
        Pair.of(TITLE_NAME, serverUpdateProblemRequest.getTitle()),
        Pair.of(DESCRIPTION_NAME, serverUpdateProblemRequest.getDescription()),
        Pair.of(IDENTIFICATION_NUMBER_NAME, serverUpdateProblemRequest.getIdentificationNumber()),
        Pair.of(REFERENCE_IDENTIFICATION_NUMBER_NAME,
            serverUpdateProblemRequest.getReferenceIdentificationNumber()),
        Pair.of(NOTIFICATION_IDENTIFICATION_NUMBER_NAME,
            serverUpdateProblemRequest.getNotificationIdentificationNumber()),
        Pair.of(STATE_NAME, serverUpdateProblemRequest.getState()),
        Pair.of(REPORTER_NAME, serverUpdateProblemRequest.getReporter())));

    validateNotEmpty(
        Map.of(
            TITLE_NAME, serverUpdateProblemRequest.getTitle(),
            DESCRIPTION_NAME, serverUpdateProblemRequest.getDescription(),
            REPORTER_NAME, serverUpdateProblemRequest.getReporter()));

    validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME,
            Pair.of(serverUpdateProblemRequest.getIdentificationNumber(), PIN_PATTERN),
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
            Pair.of(serverUpdateProblemRequest.getReferenceIdentificationNumber(),
                BIN_RIN_CIN_PATTERN),
            NOTIFICATION_IDENTIFICATION_NUMBER_NAME,
            Pair.of(serverUpdateProblemRequest.getNotificationIdentificationNumber(),
                NIN_PATTERN)));

    validateExists(serverUpdateProblemRequest.getIdentificationNumber(),
        PROBLEM_IDENTIFICATION_NUMBER_NAME);
  }

}
