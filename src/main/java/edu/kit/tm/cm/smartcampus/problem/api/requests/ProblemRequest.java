package edu.kit.tm.cm.smartcampus.problem.api.requests;

import lombok.*;

/**
 * This class represents a request for a Problem.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
public class ProblemRequest {

  private String title;

  private String description;

  private String referenceIdentificationNumber;

  private String notificationIdentificationNumber;

  private String reporter;


}
