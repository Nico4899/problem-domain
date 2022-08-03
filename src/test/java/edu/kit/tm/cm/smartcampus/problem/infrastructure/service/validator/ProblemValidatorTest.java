package edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.error.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem.State;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

class ProblemValidatorTest {

  private static final String PROBLEM_IDENTIFICATION_NUMBER = "p-1";
  private static final String PROBLEM_TITLE = "Elevator is defect";
  private static final String PROBLEM_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_REPORTER = "Johannes von Geisau";
  private static final State PROBLEM_STATE = State.OPEN;
  private static final String EMPTY_STRING = "";
  private static final String INVALID_PROBLEM_IDENTIFICATION_NUMBER = "p1";
  private static final String INVALID_REFERENCE_IDENTIFICATION_NUMBER = "b1";
  private static final String INVALID_NOTIFICATION_IDENTIFICATION_NUMBER = "n1";


  private static final ProblemRepository PROBLEM_REPOSITORY = mock(ProblemRepository.class);
  private static final ProblemValidator PROBLEM_VALIDATOR = new ProblemValidator(
      PROBLEM_REPOSITORY);

  @Test
  void getValidateRegex_ShouldReturnPINPattern() {
    Assertions.assertEquals(Validator.PIN_PATTERN, PROBLEM_VALIDATOR.getValidateRegex());
  }

  @BeforeAll
  static void setUp() {
    Mockito.when(PROBLEM_REPOSITORY.existsById(PROBLEM_IDENTIFICATION_NUMBER)).thenReturn(true);
  }

  @ParameterizedTest
  @MethodSource("provideValidServerCreateProblemRequests")
  void validateCreate_ShouldNotThrowException(
      ServerCreateProblemRequest serverCreateProblemRequest) {
    Assertions.assertDoesNotThrow(
        () -> PROBLEM_VALIDATOR.validateCreate(serverCreateProblemRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidServerCreateProblemRequests")
  void validateCreate_ShouldThrowException(
      ServerCreateProblemRequest serverCreateProblemRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> PROBLEM_VALIDATOR.validateCreate(serverCreateProblemRequest));
  }

  @ParameterizedTest
  @MethodSource("provideValidServerUpdateProblemRequests")
  void validateUpdate_ShouldNotThrowException(
      ServerUpdateProblemRequest serverUpdateProblemRequest) {
    Assertions.assertDoesNotThrow(
        () -> PROBLEM_VALIDATOR.validateUpdate(serverUpdateProblemRequest));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidServerUpdateProblemRequests")
  void validateUpdate_ShouldThrowException(
      ServerUpdateProblemRequest serverUpdateProblemRequest) {
    Assertions.assertThrows(InvalidArgumentsException.class,
        () -> PROBLEM_VALIDATOR.validateUpdate(serverUpdateProblemRequest));
  }


  private static Stream<Arguments> provideValidServerCreateProblemRequests() {
    return Stream.of(
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER))
    );
  }

  private static Stream<Arguments> provideInvalidServerCreateProblemRequests() {
    return Stream.of(
        //Invalid because of null
        Arguments.of((ServerCreateProblemRequest) null),
        Arguments.of(
            new ServerCreateProblemRequest(null,
                null, null,
                null, null)),
        Arguments.of(
            new ServerCreateProblemRequest(null,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                null, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, null,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, null)),
        Arguments.of(
            new ServerCreateProblemRequest(null,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                null, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, null,
                null, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, null)),
        //Invalid because of empty
        Arguments.of(
            new ServerCreateProblemRequest(EMPTY_STRING,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                EMPTY_STRING, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, EMPTY_STRING)),
        //Invalid because of regex
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, INVALID_REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, INVALID_REFERENCE_IDENTIFICATION_NUMBER,
                INVALID_NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER)),
        Arguments.of(
            new ServerCreateProblemRequest(PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                INVALID_NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER))
    );
  }

  private static Stream<Arguments> provideValidServerUpdateProblemRequests() {
    return Stream.of(
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER, PROBLEM_STATE))
    );
  }

  private static Stream<Arguments> provideInvalidServerUpdateProblemRequests() {
    return Stream.of(
        //Invalid because of null
        Arguments.of((ServerUpdateProblemRequest) null),
        Arguments.of(
            new ServerUpdateProblemRequest(null, null,
                null, null,
                null, null, null)),
        Arguments.of(
            new ServerUpdateProblemRequest(null, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, null,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                null, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, null,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, null, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, null)),
        Arguments.of(
            new ServerUpdateProblemRequest(null, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, null,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                null, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, null,
                null, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, null, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER, null)),
        //Invalid because of empty
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, EMPTY_STRING,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                EMPTY_STRING, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, EMPTY_STRING, PROBLEM_STATE)),
        //Invalid because of regex
        Arguments.of(
            new ServerUpdateProblemRequest(INVALID_PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, INVALID_REFERENCE_IDENTIFICATION_NUMBER,
                NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                INVALID_NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, INVALID_REFERENCE_IDENTIFICATION_NUMBER,
                INVALID_NOTIFICATION_IDENTIFICATION_NUMBER, PROBLEM_REPORTER, PROBLEM_STATE)),
        Arguments.of(
            new ServerUpdateProblemRequest(INVALID_PROBLEM_IDENTIFICATION_NUMBER, PROBLEM_TITLE,
                PROBLEM_DESCRIPTION, REFERENCE_IDENTIFICATION_NUMBER,
                null, PROBLEM_REPORTER, PROBLEM_STATE))
    );
  }

}
