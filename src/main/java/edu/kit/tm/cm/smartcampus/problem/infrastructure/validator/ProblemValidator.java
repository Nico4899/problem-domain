package edu.kit.tm.cm.smartcampus.problem.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating {@link
 * Problem} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class ProblemValidator extends Validator<Problem> {

  /**
   * Instantiates a new Problem validator.
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
  public void validateCreate(Problem object) {
    validateBase(object);
  }

  @Override
  public void validateUpdate(Problem object) {
    validateBase(object);
    validateExists(object.getIdentificationNumber(), PROBLEM_IDENTIFICATION_NUMBER_NAME);
  }

  private void validateBase(Problem object) {
    validateNotNull(
        Map.of(
            PROBLEM_NAME,
            object,
            TITLE_NAME,
            object.getTitle(),
            DESCRIPTION_NAME,
            object.getDescription(),
            IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber(),
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
            object.getReferenceIdentificationNumber(),
            NOTIFICATION_IDENTIFICATION_NUMBER_NAME,
            object.getNotificationIdentificationNumber(),
            STATE_NAME,
            object.getState(),
            REPORTER_NAME,
            object.getReporter()));

    validateNotEmpty(
        Map.of(
            TITLE_NAME, object.getTitle(),
            DESCRIPTION_NAME, object.getDescription(),
            REPORTER_NAME, object.getReporter()));

    validateMatchesRegex(
        Map.of(
            IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getIdentificationNumber(), PIN_PATTERN),
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getReferenceIdentificationNumber(), BIN_RIN_CIN_PATTERN),
            NOTIFICATION_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getNotificationIdentificationNumber(), NIN_PATTERN)));
  }
}
