package edu.kit.tm.cm.smartcampus.problem.api.payload;

import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProblemRequest {

    private ProblemState state;

    private String title;

    private String description;

    private String creationData;

    private String reporter;

}
