package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.ProblemController;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator.ProblemValidator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

import static org.mockito.Mockito.mock;

public class ServiceTests {

  /*private static final String PROBLEM_IDENTIFICATION_NUMBER = "p-1";
  private static final String PROBLEM_TITLE = "Elevator is defect";
  private static final String PROBLEM_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_REPORTER = "Johannes von Geisau";
  private static final Problem.State PROBLEM_STATE_OPEN = Problem.State.OPEN;

  private static final String UPDATED_PROBLEM_TITLE = "Elevator is defect!";
  private static final String UPDATED_PROBLEM_DESCRIPTION = "Cannot reach any higher floor than "
      + "second level because of a defect.";
  private static final String UPDATED_REFERENCE_IDENTIFICATION_NUMBER = "b-2";
  private static final String UPDATED_NOTIFICATION_IDENTIFICATION_NUMBER = "n-2";
  private static final String UPDATED_PROBLEM_REPORTER = "Johannes von Geisau";
  private static final Problem.State UPDATED_PROBLEM_STATE = Problem.State.ACCEPTED;

  private static final ProblemRepository PROBLEM_REPOSITORY = mock(ProblemRepository.class);
  private static final ProblemValidator PROBLEM_VALIDATOR = mock(ProblemValidator.class);

  private static final Service SERVICE = new Service(PROBLEM_REPOSITORY, PROBLEM_VALIDATOR);
  private static ServerCreateProblemRequest serverCreateProblemRequest;
  private static ServerUpdateProblemRequest serverUpdateProblemRequest;
  private static String problemIdentificationNumber;
  private static Problem problemWithIdentificationNumber;
  private static Problem problemWithoutIdentificationNumber;





  @Autowired
  ProblemController problemController;

  @BeforeEach
  public void setUp() {
    serverCreateProblemRequest = new ServerCreateProblemRequest(
        PROBLEM_TITLE,
        PROBLEM_DESCRIPTION,
        REFERENCE_IDENTIFICATION_NUMBER,
        NOTIFICATION_IDENTIFICATION_NUMBER,
        PROBLEM_REPORTER
    );

    problemWithoutIdentificationNumber = new Problem();
    problemWithoutIdentificationNumber.setState(PROBLEM_STATE_OPEN);
    problemWithoutIdentificationNumber.setDescription(PROBLEM_DESCRIPTION);
    problemWithoutIdentificationNumber.setReporter(PROBLEM_REPORTER);
    problemWithoutIdentificationNumber.setTitle(PROBLEM_TITLE);
    problemWithoutIdentificationNumber.setNotificationIdentificationNumber(NOTIFICATION_IDENTIFICATION_NUMBER);
    problemWithoutIdentificationNumber.setReferenceIdentificationNumber(REFERENCE_IDENTIFICATION_NUMBER);
    problemWithoutIdentificationNumber.setCreationTime(new Timestamp(System.currentTimeMillis()));
    problemWithoutIdentificationNumber.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    problemWithIdentificationNumber = new Problem();
    problemWithIdentificationNumber.setIdentificationNumber(PROBLEM_IDENTIFICATION_NUMBER);
    problemWithIdentificationNumber.setState(PROBLEM_STATE_OPEN);
    problemWithIdentificationNumber.setDescription(PROBLEM_DESCRIPTION);
    problemWithIdentificationNumber.setReporter(PROBLEM_REPORTER);
    problemWithIdentificationNumber.setTitle(PROBLEM_TITLE);
    problemWithIdentificationNumber.setNotificationIdentificationNumber(NOTIFICATION_IDENTIFICATION_NUMBER);
    problemWithIdentificationNumber.setReferenceIdentificationNumber(REFERENCE_IDENTIFICATION_NUMBER);
    problemWithIdentificationNumber.setCreationTime(new Timestamp(System.currentTimeMillis()));
    problemWithIdentificationNumber.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    serverUpdateProblemRequest = new ServerUpdateProblemRequest(
        PROBLEM_IDENTIFICATION_NUMBER,
        UPDATED_PROBLEM_TITLE,
        UPDATED_PROBLEM_DESCRIPTION,
        UPDATED_REFERENCE_IDENTIFICATION_NUMBER,
        UPDATED_NOTIFICATION_IDENTIFICATION_NUMBER,
        UPDATED_PROBLEM_REPORTER,
        UPDATED_PROBLEM_STATE
    );



  }

  /*@ParameterizedTest
  @ArgumentsSource(edu.kit.tm.cm.smartcampus.problem.api.controller.problem.ControllerTests.ServerCreateProblemRequestsProvider.class)
  void createProblemsTest(ServerCreateProblemRequest serverCreateProblemRequest) {
    String problemIdentificationNumber = problemController.createProblem(serverCreateProblemRequest)
        .getIdentificationNumber();
    Assertions.assertDoesNotThrow( () -> problemController.getProblem(problemIdentificationNumber));

    Assertions.assertEquals(problemIdentificationNumber,
        problemController.getProblem(problemIdentificationNumber).getIdentificationNumber());
    Assertions.assertEquals(serverCreateProblemRequest.getTitle(),
        problemController.getProblem(problemIdentificationNumber).getTitle());
    Assertions.assertEquals(serverCreateProblemRequest.getDescription(),
        problemController.getProblem(problemIdentificationNumber).getDescription());
    Assertions.assertEquals(serverCreateProblemRequest.getNotificationIdentificationNumber(),
        problemController.getProblem(problemIdentificationNumber).getNotificationIdentificationNumber());
    Assertions.assertEquals(serverCreateProblemRequest.getReferenceIdentificationNumber(),
        problemController.getProblem(problemIdentificationNumber).getReferenceIdentificationNumber());
    Assertions.assertEquals(serverCreateProblemRequest.getReporter(),
        problemController.getProblem(problemIdentificationNumber).getReporter());
    Assertions.assertEquals(Problem.State.OPEN,
        problemController.getProblem(problemIdentificationNumber).getState());
  }

  @Test
  void createProblem_ShouldCreateNewProblem() {
    //todo irgendwie mocken
    Problem createdProblem = SERVICE.createProblem(serverCreateProblemRequest);
    Assertions.assertEquals(serverCreateProblemRequest.getTitle(),
        createdProblem.getTitle());
    Assertions.assertEquals(serverCreateProblemRequest.getDescription(),
        createdProblem.getDescription());
    Assertions.assertEquals(serverCreateProblemRequest.getNotificationIdentificationNumber(),
        createdProblem.getNotificationIdentificationNumber());
    Assertions.assertEquals(serverCreateProblemRequest.getReferenceIdentificationNumber(),
        createdProblem.getReferenceIdentificationNumber());
    Assertions.assertEquals(serverCreateProblemRequest.getReporter(),
        createdProblem.getReporter());
    Assertions.assertEquals(createdProblem.getState(), Problem.State.OPEN);
  }


  private static class ServerCreateProblemRequestsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
      return Stream.of(
          Arguments.of(testProblemCreateRequestsMap.get(PROBLEM_1_TITLE)),
          Arguments.of(testProblemCreateRequestsMap.get(PROBLEM_2_TITLE))
      );
    }
  }*/

}
