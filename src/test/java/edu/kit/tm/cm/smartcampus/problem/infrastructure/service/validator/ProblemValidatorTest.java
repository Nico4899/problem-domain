package edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.error.exceptions.InvalidArgumentsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProblemValidatorTest {

  private static final String PROBLEM_TITLE = "Elevator is defect";
  private static final String PROBLEM_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_REPORTER = "Johannes von Geisau";
  private static final String EMPTY_STRING = "";
  private static final String INVALID_REFERENCE_IDENTIFICATION_NUMBER = "b1";

  private final ProblemRepository PROBLEM_REPOSITORY = mock(ProblemRepository.class);
  private final ProblemValidator PROBLEM_VALIDATOR = new ProblemValidator(PROBLEM_REPOSITORY);

  @Test
  void getValidateRegex_ShouldReturnPINPattern() {
    Assertions.assertEquals(PROBLEM_VALIDATOR.getValidateRegex(), Validator.PIN_PATTERN);
  }

  @Nested
  class ValidateCreateTest {

    private ServerCreateProblemRequest serverCreateProblemRequest;

    @BeforeEach
    void beforeEachValidateCreateTest() {
      serverCreateProblemRequest = new ServerCreateProblemRequest(PROBLEM_TITLE,
          PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER, NOTIFICATION_IDENTIFICATION_NUMBER,
          PROBLEM_REPORTER);
    }

    @Test
    void validateCreate_ShouldNotThrowException() {
      Assertions.assertDoesNotThrow(
          () -> PROBLEM_VALIDATOR.validateCreate(serverCreateProblemRequest));
    }

    @Test
    void validateCreate_ShouldThrowInvalidArgumentsExceptionBecauseOfNull() {
      serverCreateProblemRequest.setTitle(null);
      Assertions.assertThrows(InvalidArgumentsException.class,
          () -> PROBLEM_VALIDATOR.validateCreate(serverCreateProblemRequest));
    }

    @Test
    void validateCreate_ShouldThrowInvalidArgumentsExceptionBecauseOfEmpty() {
      serverCreateProblemRequest.setTitle(EMPTY_STRING);
      Assertions.assertThrows(InvalidArgumentsException.class,
          () -> PROBLEM_VALIDATOR.validateCreate(serverCreateProblemRequest));
    }

    @Test
    void validateCreate_ShouldThrowInvalidArgumentsExceptionBecauseOfRegex() {
      serverCreateProblemRequest.setReferenceIdentificationNumber(INVALID_REFERENCE_IDENTIFICATION_NUMBER);
      Assertions.assertThrows(InvalidArgumentsException.class,
          () -> PROBLEM_VALIDATOR.validateCreate(serverCreateProblemRequest));
    }

  }

  @Nested
  class ValidateUpdateTest {

    @BeforeEach
    void beforeEachValidateUpdateTest() {

    }

  }

}
