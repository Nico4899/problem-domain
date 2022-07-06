package edu.kit.tm.cm.smartcampus.problem.logic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Problem {

    private String refId;

    private String id;

    private String nin;

    private String state;

    private String title;

    private String description;

    private String creationData;

    private String reporter;

}
