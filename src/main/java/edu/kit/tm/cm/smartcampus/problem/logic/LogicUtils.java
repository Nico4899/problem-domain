package edu.kit.tm.cm.smartcampus.problem.logic;

import edu.kit.tm.cm.smartcampus.problem.api.payload.ProblemRequest;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemState;

public final class LogicUtils {

    /*
    Initializes new problem with existing data from problemRequest
     */
    public static Problem convertProblemRequestToProblem(ProblemRequest problemRequest) {

        String title = problemRequest.getTitle();
        String description = problemRequest.getDescription();
        String creationData = problemRequest.getCreationData();
        String reporter = problemRequest.getReporter();
        ProblemState state = problemRequest.getState();

        return new Problem(title, description, creationData, reporter, state);

    }

}
