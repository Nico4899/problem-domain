package edu.kit.tm.cm.smartcampus.problem.api.controller.problem;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem.State;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProblemControllerTest {

  private static final String PROBLEM_TITLE = "Elevator is defect";
  private static final String PROBLEM_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_REPORTER = "Johannes von Geisau";
  private static final State PROBLEM_STATE = State.OPEN;

  private static final String UPDATED_PROBLEM_TITLE = "Elevator is defect!";
  private static final String UPDATED_PROBLEM_DESCRIPTION = "Cannot reach any higher floor than "
      + "second level because of a defect.";
  private static final String UPDATED_REFERENCE_IDENTIFICATION_NUMBER = "b-2";
  private static final String UPDATED_NOTIFICATION_IDENTIFICATION_NUMBER = "n-2";
  private static final String UPDATED_PROBLEM_REPORTER = "Johannes von Geisau";
  private static final State UPDATED_PROBLEM_STATE = State.ACCEPTED;

  private static final Service SERVICE = mock(Service.class);
  private static final ProblemController PROBLEM_CONTROLLER = new ProblemController(SERVICE);
  private static ServerCreateProblemRequest serverCreateProblemRequest;
  private static ServerUpdateProblemRequest serverUpdateProblemRequest;
  private static String problemIdentificationNumber;
  private static Problem problem;

  @BeforeEach
  public void setUp() {
    serverCreateProblemRequest = new ServerCreateProblemRequest(
        PROBLEM_TITLE,
        PROBLEM_DESCRIPTION,
        REFERENCE_IDENTIFICATION_NUMBER,
        NOTIFICATION_IDENTIFICATION_NUMBER,
        PROBLEM_REPORTER
    );

    problem = new Problem();
    problem.setState(PROBLEM_STATE);
    problem.setDescription(PROBLEM_DESCRIPTION);
    problem.setReporter(PROBLEM_REPORTER);
    problem.setTitle(PROBLEM_TITLE);
    problem.setNotificationIdentificationNumber(NOTIFICATION_IDENTIFICATION_NUMBER);
    problem.setReferenceIdentificationNumber(REFERENCE_IDENTIFICATION_NUMBER);
    problem.setCreationTime(new Timestamp(System.currentTimeMillis()));
    problem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    problemIdentificationNumber = problem.getIdentificationNumber();

    serverUpdateProblemRequest = new ServerUpdateProblemRequest(
        problemIdentificationNumber,
        UPDATED_PROBLEM_TITLE,
        UPDATED_PROBLEM_DESCRIPTION,
        UPDATED_REFERENCE_IDENTIFICATION_NUMBER,
        UPDATED_NOTIFICATION_IDENTIFICATION_NUMBER,
        UPDATED_PROBLEM_REPORTER,
        UPDATED_PROBLEM_STATE
    );
  }

  @Test
  void createProblem_ShouldCreateNewProblem() {
    Mockito.when(SERVICE.createProblem(serverCreateProblemRequest)).thenReturn(problem);
    Problem createdProblem = PROBLEM_CONTROLLER.createProblem(serverCreateProblemRequest);
    Assertions.assertEquals(createdProblem, problem);
  }

  @Test
  void updateProblem_ShouldUpdateProblem() {
    problem.setState(UPDATED_PROBLEM_STATE);
    problem.setDescription(UPDATED_PROBLEM_DESCRIPTION);
    problem.setReporter(UPDATED_PROBLEM_REPORTER);
    problem.setTitle(UPDATED_PROBLEM_TITLE);
    problem.setNotificationIdentificationNumber(UPDATED_NOTIFICATION_IDENTIFICATION_NUMBER);
    problem.setReferenceIdentificationNumber(UPDATED_REFERENCE_IDENTIFICATION_NUMBER);
    problem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    Mockito.when(SERVICE.updateProblem(serverUpdateProblemRequest)).thenReturn(problem);
    Problem updatedProblem = PROBLEM_CONTROLLER.updateProblem(serverUpdateProblemRequest);
    updatedProblem.setLastModifiedTime(problem.getLastModifiedTime());
    Assertions.assertEquals(updatedProblem, problem);
  }

  @Test
  void getProblem_ShouldGetProblem(){
    Mockito.when(SERVICE.getProblem(problemIdentificationNumber)).thenReturn(problem);
    Problem fetchedProblem = PROBLEM_CONTROLLER.getProblem(problemIdentificationNumber);
    Assertions.assertEquals(fetchedProblem, problem);
  }

  @Test
  void listProblems_ShouldListProblems(){
    Mockito.when(SERVICE.listProblems()).thenReturn(List.of(problem));
    Collection<Problem> fetchedProblems = PROBLEM_CONTROLLER.listProblems();
    Assertions.assertEquals(fetchedProblems, List.of(problem));
  }

  @Test
  void removeProblem_ShouldRemoveProblem(){
    PROBLEM_CONTROLLER.removeProblem(problemIdentificationNumber);
    Mockito.verify(SERVICE).removeProblem(problemIdentificationNumber);
  }

}
