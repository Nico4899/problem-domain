package edu.kit.tm.cm.smartcampus.problem.api.controller.problem;

import edu.kit.tm.cm.smartcampus.problem.api.controller.dto.ServerCreateProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.service.Service;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

@SpringJUnitConfig
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTests {

  /*private static final Service SERVICE = mock(Service.class);
  private static final ProblemController PROBLEM_CONTROLLER = new ProblemController(SERVICE);
  private static final String PROBLEM_1_TITLE = "Elevator is defect";
  private static final String PROBLEM_2_TITLE = "Outlet is defect";
  private static final String PROBLEM_1_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String PROBLEM_2_DESCRIPTION = "Outlet does not provide power";
  private static final String BUILDING_1_IDENTIFICATION_NUMBER = "b-1";
  private static final String BUILDING_2_IDENTIFICATION_NUMBER = "b-2";
  private static final String NOTIFICATION_1_IDENTIFICATION_NUMBER = "n-1";
  private static final String NOTIFICATION_2_IDENTIFICATION_NUMBER = "n-2";
  private static final String PROBLEM_REPORTER_1 = "Johannes von Geisau";
  private static final String PROBLEM_REPORTER_2 = "Bastian Bacher";

  private static final Map<String, ServerCreateProblemRequest> testProblemCreateRequestsMap = new HashMap<>();
  private static Collection<ServerCreateProblemRequest> testProblemCreateRequests;

  @Autowired
  ProblemController problemController;

  @BeforeAll
  public static void setUp() { buildTestMappings();
  }

  private static void buildTestMappings() {


    ServerCreateProblemRequest createProblemRequest1 = new ServerCreateProblemRequest(
        PROBLEM_1_TITLE,
        PROBLEM_1_DESCRIPTION,
        BUILDING_1_IDENTIFICATION_NUMBER,
        NOTIFICATION_1_IDENTIFICATION_NUMBER,
        PROBLEM_REPORTER_1
    );
    ServerCreateProblemRequest createProblemRequest2 = new ServerCreateProblemRequest(
        PROBLEM_2_TITLE,
        PROBLEM_2_DESCRIPTION,
        BUILDING_2_IDENTIFICATION_NUMBER,
        NOTIFICATION_2_IDENTIFICATION_NUMBER,
        PROBLEM_REPORTER_2
    );
    testProblemCreateRequestsMap.put(PROBLEM_1_TITLE, createProblemRequest1);
    testProblemCreateRequestsMap.put(PROBLEM_2_TITLE, createProblemRequest2);
    testProblemCreateRequests = testProblemCreateRequestsMap.values();
  }

  @ParameterizedTest
  @ArgumentsSource(ServerCreateProblemRequestsProvider.class)
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
