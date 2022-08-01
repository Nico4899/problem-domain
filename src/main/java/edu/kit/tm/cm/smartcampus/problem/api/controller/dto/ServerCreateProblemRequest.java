package edu.kit.tm.cm.smartcampus.problem.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a request for a Problem.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateProblemRequest {

  private String title;
  private String description;
  private String referenceIdentificationNumber;
  private String notificationIdentificationNumber;
  private String reporter;
}
