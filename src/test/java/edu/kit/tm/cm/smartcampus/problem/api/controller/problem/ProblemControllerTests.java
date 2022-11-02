package edu.kit.tm.cm.smartcampus.problem.api.controller.problem;

import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem.State;
import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProblemControllerTests {

  //Attributes
  private static final String PROBLEM_IDENTIFICATION_NUMBER = "p-1";
  private static final String PROBLEM_TITLE = "Elevator is defect";
  private static final String PROBLEM_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_REPORTER = "Johannes von Geisau";
  private static final State PROBLEM_STATE = State.OPEN;
  private static final String UPDATE_PROBLEM_TITLE = "Elevator is defect!";
  private static final String UPDATE_PROBLEM_DESCRIPTION = "Cannot reach any higher floor than "
      + "second level because of a defect.";
  private static final String UPDATE_REFERENCE_IDENTIFICATION_NUMBER = "b-2";
  private static final String UPDATE_NOTIFICATION_IDENTIFICATION_NUMBER = "n-2";
  private static final String UPDATE_PROBLEM_REPORTER = "Johannes von Geisau";
  private static final State UPDATE_PROBLEM_STATE = State.ACCEPTED;
  //Mocks
  private static final Service SERVICE = mock(Service.class);
  private static final ProblemController PROBLEM_CONTROLLER = new ProblemController(SERVICE);
  //Requests
  private static final ServerCreateProblemRequest SERVER_CREATE_PROBLEM_REQUEST =
      new ServerCreateProblemRequest(
          PROBLEM_TITLE,
          PROBLEM_DESCRIPTION,
          REFERENCE_IDENTIFICATION_NUMBER,
          NOTIFICATION_IDENTIFICATION_NUMBER,
          PROBLEM_REPORTER
      );
  private static final ServerUpdateProblemRequest SERVER_UPDATE_PROBLEM_REQUEST =
      new ServerUpdateProblemRequest(
          PROBLEM_IDENTIFICATION_NUMBER,
          UPDATE_PROBLEM_TITLE,
          UPDATE_PROBLEM_DESCRIPTION,
          UPDATE_REFERENCE_IDENTIFICATION_NUMBER,
          UPDATE_NOTIFICATION_IDENTIFICATION_NUMBER,
          UPDATE_PROBLEM_REPORTER,
          UPDATE_PROBLEM_STATE
      );
  //Instances
  private static final Problem PROBLEM = new Problem();

  @BeforeAll
  public static void setUp() {
    PROBLEM.setIdentificationNumber(PROBLEM_IDENTIFICATION_NUMBER);
    PROBLEM.setState(PROBLEM_STATE);
    PROBLEM.setDescription(PROBLEM_DESCRIPTION);
    PROBLEM.setReporter(PROBLEM_REPORTER);
    PROBLEM.setTitle(PROBLEM_TITLE);
    PROBLEM.setNotificationIdentificationNumber(NOTIFICATION_IDENTIFICATION_NUMBER);
    PROBLEM.setReferenceIdentificationNumber(REFERENCE_IDENTIFICATION_NUMBER);
    PROBLEM.setCreationTime(new Timestamp(System.currentTimeMillis()));
    PROBLEM.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
  }

  @Test
  void createProblem_ShouldCreateNewProblem() {
    PROBLEM_CONTROLLER.createProblem(SERVER_CREATE_PROBLEM_REQUEST);
    Mockito.verify(SERVICE).createProblem(SERVER_CREATE_PROBLEM_REQUEST);
  }

  @Test
  void updateProblem_ShouldUpdateProblem() {
    PROBLEM_CONTROLLER.updateProblem(SERVER_UPDATE_PROBLEM_REQUEST);
    Mockito.verify(SERVICE).updateProblem(SERVER_UPDATE_PROBLEM_REQUEST);
  }

  @Test
  void getProblem_ShouldGetProblem() {
    PROBLEM_CONTROLLER.getProblem(PROBLEM_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).getProblem(PROBLEM_IDENTIFICATION_NUMBER);
  }

  @Test
  void listProblems_ShouldListProblems() {
    PROBLEM_CONTROLLER.listProblems();
    Mockito.verify(SERVICE).listProblems();
  }

  @Test
  void removeProblem_ShouldRemoveProblem() {
    PROBLEM_CONTROLLER.removeProblem(PROBLEM_IDENTIFICATION_NUMBER);
    Mockito.verify(SERVICE).removeProblem(PROBLEM_IDENTIFICATION_NUMBER);
  }

}
