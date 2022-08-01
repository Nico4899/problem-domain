package edu.kit.tm.cm.smartcampus.problem.api.controller.problem;

import edu.kit.tm.cm.smartcampus.problem.api.controller.dto.ServerCreateProblemRequest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class ControllerTests {

  private static final String PROBLEM_1 = "Elevator is defect";
  private static final String PROBLEM_2 = "Outlet is defect";
  private static final String PROBLEM_1_DESCRIPTION = "Cannot reach any higher floor than second level";
  private static final String PROBLEM_2_DESCRIPTION = "Outlet does not provide power";
  private static final String BUILDING_1_IDENTIFICATION_NUMBER = "b-1";
  private static final String BUILDING_2_IDENTIFICATION_NUMBER = "b-2";
  private static final String NOTIFICATION_1_IDENTIFICATION_NUMBER = "n-1";
  private static final String NOTIFICATION_2_IDENTIFICATION_NUMBER = "n-2";
  private static final String PROBLEM_REPORTER_1 = "Johannes von Geissau";
  private static final String PROBLEM_REPORTER_2 = "Bastian Bacher";

  private static Collection<ServerCreateProblemRequest> testProblemCreateRequests;

  @Autowired
  ProblemController problemController;


  @BeforeAll
  public static void setUp() { buildTestMappings();
  }

  private static void buildTestMappings() {

    testProblemCreateRequests.addAll(
        List.of(
            new ServerCreateProblemRequest(
                PROBLEM_1,
                PROBLEM_1_DESCRIPTION,
                BUILDING_1_IDENTIFICATION_NUMBER,
                NOTIFICATION_1_IDENTIFICATION_NUMBER,
                PROBLEM_REPORTER_1
            ),
            new ServerCreateProblemRequest(
                PROBLEM_2,
                PROBLEM_2_DESCRIPTION,
                BUILDING_2_IDENTIFICATION_NUMBER,
                NOTIFICATION_2_IDENTIFICATION_NUMBER,
                PROBLEM_REPORTER_2
            )
        )
    );
  }
}
