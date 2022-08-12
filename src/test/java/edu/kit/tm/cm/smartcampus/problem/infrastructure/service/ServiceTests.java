package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import edu.kit.tm.cm.smartcampus.problem.TestUtils;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto.ServerUpdateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.validator.ProblemValidator;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServiceTests {

  //Attributes
  private static final String PROBLEM_IDENTIFICATION_NUMBER = "p-1";
  private static final String PROBLEM_TITLE = "Elevator is defect";
  private static final String PROBLEM_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String PROBLEM_REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String PROBLEM_NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_PROBLEM_REPORTER = "Johannes von Geisau";
  private static final Problem.State PROBLEM_PROBLEM_STATE_OPEN = Problem.State.OPEN;
  private static final String UPDATED_PROBLEM_TITLE = "Elevator is still defect!";
  private static final String UPDATED_PROBLEM_DESCRIPTION = "Cannot reach any higher floor than "
      + "second level because of a defect.";
  private static final String UPDATED_PROBLEM_REFERENCE_IDENTIFICATION_NUMBER = "b-2";
  private static final String UPDATED_PROBLEM_NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String UPDATED_PROBLEM_PROBLEM_REPORTER = "Bastian Bacher";
  private static final Problem.State UPDATED_PROBLEM_PROBLEM_STATE = Problem.State.ACCEPTED;
  //Mocks
  private static final ProblemRepository PROBLEM_REPOSITORY = mock(ProblemRepository.class);
  private static final ProblemValidator PROBLEM_VALIDATOR = mock(ProblemValidator.class);
  private static final Service SERVICE = new Service(PROBLEM_REPOSITORY, PROBLEM_VALIDATOR);
  //Requests
  private static final ServerCreateProblemRequest SERVER_CREATE_PROBLEM_REQUEST =
      new ServerCreateProblemRequest(
          PROBLEM_TITLE,
          PROBLEM_DESCRIPTION,
          PROBLEM_REFERENCE_IDENTIFICATION_NUMBER,
          PROBLEM_NOTIFICATION_IDENTIFICATION_NUMBER,
          PROBLEM_PROBLEM_REPORTER
      );
  private static final ServerUpdateProblemRequest SERVER_UPDATE_PROBLEM_REQUEST = new ServerUpdateProblemRequest(
      PROBLEM_IDENTIFICATION_NUMBER,
      UPDATED_PROBLEM_TITLE,
      UPDATED_PROBLEM_DESCRIPTION,
      UPDATED_PROBLEM_REFERENCE_IDENTIFICATION_NUMBER,
      UPDATED_PROBLEM_NOTIFICATION_IDENTIFICATION_NUMBER,
      UPDATED_PROBLEM_PROBLEM_REPORTER,
      UPDATED_PROBLEM_PROBLEM_STATE
  );
  //Instances
  private static final Problem PROBLEM = new Problem();
  private static final Problem UPDATED_PROBLEM = new Problem();

  @BeforeEach
  public void setUp() {
    //initialize problem(s) (without creation- and modification time!)
    PROBLEM.setIdentificationNumber(PROBLEM_IDENTIFICATION_NUMBER);
    PROBLEM.setState(PROBLEM_PROBLEM_STATE_OPEN);
    PROBLEM.setDescription(PROBLEM_DESCRIPTION);
    PROBLEM.setReporter(PROBLEM_PROBLEM_REPORTER);
    PROBLEM.setTitle(PROBLEM_TITLE);
    PROBLEM.setNotificationIdentificationNumber(PROBLEM_NOTIFICATION_IDENTIFICATION_NUMBER);
    PROBLEM.setReferenceIdentificationNumber(PROBLEM_REFERENCE_IDENTIFICATION_NUMBER);

    UPDATED_PROBLEM.setIdentificationNumber(PROBLEM_IDENTIFICATION_NUMBER);
    UPDATED_PROBLEM.setState(UPDATED_PROBLEM_PROBLEM_STATE);
    UPDATED_PROBLEM.setDescription(UPDATED_PROBLEM_DESCRIPTION);
    UPDATED_PROBLEM.setReporter(UPDATED_PROBLEM_PROBLEM_REPORTER);
    UPDATED_PROBLEM.setTitle(UPDATED_PROBLEM_TITLE);
    UPDATED_PROBLEM.setNotificationIdentificationNumber(
        UPDATED_PROBLEM_NOTIFICATION_IDENTIFICATION_NUMBER);
    UPDATED_PROBLEM.setReferenceIdentificationNumber(
        UPDATED_PROBLEM_REFERENCE_IDENTIFICATION_NUMBER);
  }

  @Test
  void listProblems_ShouldListProblems() {
    List<Problem> problemListForMock = List.of(PROBLEM);
    Mockito.when(PROBLEM_REPOSITORY.findAll()).thenReturn(problemListForMock);
    Assertions.assertEquals(SERVICE.listProblems(), problemListForMock);
  }

  @Test
  void getProblem_ShouldGetProblem() {
    Mockito.when(PROBLEM_REPOSITORY.findById(PROBLEM_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(PROBLEM));
    Assertions.assertEquals(SERVICE.getProblem(PROBLEM_IDENTIFICATION_NUMBER), PROBLEM);
  }

  @Test
  void createProblem_ShouldCreateNewProblem() {
    Mockito.when(PROBLEM_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);

    Timestamp beforeCreate = new Timestamp(System.currentTimeMillis());
    Problem createdProblem = SERVICE.createProblem(SERVER_CREATE_PROBLEM_REQUEST);
    Timestamp afterCreate = new Timestamp(System.currentTimeMillis());

    Assertions.assertTrue(TestUtils.problemsAreEqual(PROBLEM, createdProblem));
    Assertions.assertTrue(createdProblem.getCreationTime().getNanos() >= beforeCreate.getNanos()
        && createdProblem.getCreationTime().getNanos() <= afterCreate.getNanos());
    Assertions.assertTrue(createdProblem.getLastModifiedTime().getNanos() >= beforeCreate.getNanos()
        && createdProblem.getLastModifiedTime().getNanos() <= afterCreate.getNanos());
  }

  @Test
  void updateProblem_ShouldUpdateProblem() {
    Timestamp creationTimeMock = new Timestamp(System.currentTimeMillis());
    //originalProblem is only used for the original creation time (see SERVICE.updateProblem(...))
    Problem originalProblem = new Problem();
    originalProblem.setCreationTime(creationTimeMock);
    Mockito.when(PROBLEM_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Mockito.when(PROBLEM_REPOSITORY.findById(PROBLEM_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(originalProblem));

    Timestamp beforeUpdate = new Timestamp(System.currentTimeMillis());
    Problem updatedProblem = SERVICE.updateProblem(SERVER_UPDATE_PROBLEM_REQUEST);
    Timestamp afterUpdate = new Timestamp(System.currentTimeMillis());

    Assertions.assertTrue(TestUtils.problemsAreEqual(updatedProblem, UPDATED_PROBLEM));
    Assertions.assertTrue(updatedProblem.getLastModifiedTime().getNanos() >= beforeUpdate.getNanos()
        && updatedProblem.getLastModifiedTime().getNanos() <= afterUpdate.getNanos());
    Assertions.assertTrue(beforeUpdate.getNanos() >= updatedProblem.getCreationTime().getNanos());
  }

  @Test
  void removeProblem_ShouldRemoveProblem() {
    SERVICE.removeProblem(PROBLEM_IDENTIFICATION_NUMBER);
    Mockito.verify(PROBLEM_REPOSITORY).deleteById(PROBLEM_IDENTIFICATION_NUMBER);
  }

}
