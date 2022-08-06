package edu.kit.tm.cm.smartcampus.problem.infrastructure.service;

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

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ServiceTests {

  //Attributes
  private static final String PROBLEM_1_IDENTIFICATION_NUMBER = "p-1";
  private static final String PROBLEM_1_TITLE = "Elevator is defect";
  private static final String PROBLEM_1_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String PROBLEM_1_REFERENCE_IDENTIFICATION_NUMBER = "b-1";
  private static final String PROBLEM_1_NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String PROBLEM_1_PROBLEM_REPORTER = "Johannes von Geisau";
  private static final Problem.State PROBLEM_1_PROBLEM_STATE_OPEN = Problem.State.OPEN;
  private static final String PROBLEM_2_IDENTIFICATION_NUMBER = "p-2";
  private static final String PROBLEM_2_TITLE = "No toilet paper";
  private static final String PROBLEM_2_DESCRIPTION = "Cannot use the toilet because of the lack of toilet paper";
  private static final String PROBLEM_2_REFERENCE_IDENTIFICATION_NUMBER = "r-1";
  private static final String PROBLEM_2_NOTIFICATION_IDENTIFICATION_NUMBER = "n-2";
  private static final String PROBLEM_2_PROBLEM_REPORTER = "Jonathan Kramer";
  private static final Problem.State PROBLEM_2_PROBLEM_STATE_OPEN = Problem.State.OPEN;
  private static final String UPDATED_PROBLEM_1_TITLE = "Elevator is still defect!";
  private static final String UPDATED_PROBLEM_1_DESCRIPTION = "Cannot reach any higher floor than "
      + "second level because of a defect.";
  private static final String UPDATED_PROBLEM_1_REFERENCE_IDENTIFICATION_NUMBER = "b-2";
  private static final String UPDATED_PROBLEM_1_NOTIFICATION_IDENTIFICATION_NUMBER = "n-1";
  private static final String UPDATED_PROBLEM_1_PROBLEM_REPORTER = "Bastian Bacher";
  private static final Problem.State UPDATED_PROBLEM_1_PROBLEM_STATE = Problem.State.ACCEPTED;
  //Mocks
  private static final ProblemRepository PROBLEM_REPOSITORY = mock(ProblemRepository.class);
  private static final ProblemValidator PROBLEM_VALIDATOR = mock(ProblemValidator.class);
  private static final Service SERVICE = new Service(PROBLEM_REPOSITORY, PROBLEM_VALIDATOR);
  //Requests
  private static ServerCreateProblemRequest serverCreateProblemRequest;
  private static ServerUpdateProblemRequest serverUpdateProblemRequest;
  //Instances
  private static Problem problem1;
  private static Problem updatedProblem1;
  private static Collection<Problem> listOfProblems;


  @BeforeEach
  public void setUp() {
    serverCreateProblemRequest = new ServerCreateProblemRequest(
        PROBLEM_1_TITLE,
        PROBLEM_1_DESCRIPTION,
        PROBLEM_1_REFERENCE_IDENTIFICATION_NUMBER,
        PROBLEM_1_NOTIFICATION_IDENTIFICATION_NUMBER,
        PROBLEM_1_PROBLEM_REPORTER
    );
    problem1 = new Problem();
    problem1.setIdentificationNumber(PROBLEM_1_IDENTIFICATION_NUMBER);
    problem1.setState(PROBLEM_1_PROBLEM_STATE_OPEN);
    problem1.setDescription(PROBLEM_1_DESCRIPTION);
    problem1.setReporter(PROBLEM_1_PROBLEM_REPORTER);
    problem1.setTitle(PROBLEM_1_TITLE);
    problem1.setNotificationIdentificationNumber(PROBLEM_1_NOTIFICATION_IDENTIFICATION_NUMBER);
    problem1.setReferenceIdentificationNumber(PROBLEM_1_REFERENCE_IDENTIFICATION_NUMBER);
    problem1.setCreationTime(new Timestamp(System.currentTimeMillis()));
    problem1.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    Problem problem2 = new Problem();
    problem2.setIdentificationNumber(PROBLEM_2_IDENTIFICATION_NUMBER);
    problem2.setState(PROBLEM_2_PROBLEM_STATE_OPEN);
    problem2.setDescription(PROBLEM_2_DESCRIPTION);
    problem2.setReporter(PROBLEM_2_PROBLEM_REPORTER);
    problem2.setTitle(PROBLEM_2_TITLE);
    problem2.setNotificationIdentificationNumber(PROBLEM_2_NOTIFICATION_IDENTIFICATION_NUMBER);
    problem2.setReferenceIdentificationNumber(PROBLEM_2_REFERENCE_IDENTIFICATION_NUMBER);
    problem2.setCreationTime(new Timestamp(System.currentTimeMillis()));
    problem2.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    serverUpdateProblemRequest = new ServerUpdateProblemRequest(
        PROBLEM_1_IDENTIFICATION_NUMBER,
        UPDATED_PROBLEM_1_TITLE,
        UPDATED_PROBLEM_1_DESCRIPTION,
        UPDATED_PROBLEM_1_REFERENCE_IDENTIFICATION_NUMBER,
        UPDATED_PROBLEM_1_NOTIFICATION_IDENTIFICATION_NUMBER,
        UPDATED_PROBLEM_1_PROBLEM_REPORTER,
        UPDATED_PROBLEM_1_PROBLEM_STATE
    );

    updatedProblem1 = new Problem();
    updatedProblem1.setIdentificationNumber(PROBLEM_1_IDENTIFICATION_NUMBER);
    updatedProblem1.setState(UPDATED_PROBLEM_1_PROBLEM_STATE);
    updatedProblem1.setDescription(UPDATED_PROBLEM_1_DESCRIPTION);
    updatedProblem1.setReporter(UPDATED_PROBLEM_1_PROBLEM_REPORTER);
    updatedProblem1.setTitle(UPDATED_PROBLEM_1_TITLE);
    updatedProblem1.setNotificationIdentificationNumber(UPDATED_PROBLEM_1_NOTIFICATION_IDENTIFICATION_NUMBER);
    updatedProblem1.setReferenceIdentificationNumber(UPDATED_PROBLEM_1_REFERENCE_IDENTIFICATION_NUMBER);
    updatedProblem1.setCreationTime(problem1.getCreationTime());
    updatedProblem1.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));

    listOfProblems = List.of(problem1, problem2);

  }

  @Test
  void listProblems_ShouldListProblems() {
    Mockito.when(PROBLEM_REPOSITORY.findAll()).thenReturn(listOfProblems);
    Collection<Problem> generatedListOfProblems = SERVICE.listProblems();
    Assertions.assertEquals(listOfProblems, generatedListOfProblems);
  }

  @Test
  void getProblem_ShouldGetProblem() {
    Mockito.when(PROBLEM_REPOSITORY.findById(PROBLEM_1_IDENTIFICATION_NUMBER))
        .thenReturn(Optional.of(problem1));
    Problem fetchedProblem = SERVICE.getProblem(PROBLEM_1_IDENTIFICATION_NUMBER);
    Assertions.assertEquals(problem1, fetchedProblem);
  }

  @Test
  void createProblem_ShouldCreateNewProblem() {
    Mockito.when(PROBLEM_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);

    Timestamp beforeCreate = new Timestamp(System.currentTimeMillis());
    Problem createdProblem = SERVICE.createProblem(serverCreateProblemRequest);
    Timestamp afterCreate = new Timestamp(System.currentTimeMillis());

    Assertions.assertTrue(createdProblem.getCreationTime().getNanos() >= beforeCreate.getNanos()
        && createdProblem.getCreationTime().getNanos() <= afterCreate.getNanos());
    Assertions.assertTrue(createdProblem.getLastModifiedTime().getNanos() >= beforeCreate.getNanos()
        && createdProblem.getLastModifiedTime().getNanos() <= afterCreate.getNanos());
    Assertions.assertTrue(TestUtils.problemsAreEqual(problem1, createdProblem));
  }

  @Test
  void updateProblem_ShouldUpdateProblem() {
    Mockito.when(PROBLEM_REPOSITORY.save(any())).thenAnswer(i -> i.getArguments()[0]);
    Mockito.when(PROBLEM_REPOSITORY.findById(serverUpdateProblemRequest.getIdentificationNumber())).thenReturn(
        Optional.of(problem1));

    Timestamp beforeUpdate = new Timestamp(System.currentTimeMillis());
    Problem updatedProblem = SERVICE.updateProblem(serverUpdateProblemRequest);
    Timestamp afterUpdate = new Timestamp(System.currentTimeMillis());

    Assertions.assertTrue(updatedProblem.getLastModifiedTime().getNanos() >= beforeUpdate.getNanos()
        && updatedProblem.getLastModifiedTime().getNanos() <= afterUpdate.getNanos());
    Assertions.assertTrue(beforeUpdate.getNanos() >= updatedProblem.getCreationTime().getNanos());
    Assertions.assertTrue(TestUtils.problemsAreEqual(updatedProblem1, updatedProblem));
  }

}
