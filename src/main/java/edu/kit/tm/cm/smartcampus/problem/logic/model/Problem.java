package edu.kit.tm.cm.smartcampus.problem.logic.model;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Problem {

    private String refId;

    private String id;

    private String nin;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String creationTime;

    @NonNull
    private String reporter;

    @NonNull
    private ProblemState state;


}
