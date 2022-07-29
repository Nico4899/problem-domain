package edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateProblemRequest {
  private String title;
  private String description;
  private String referenceIdentificationNumber;
  private String notificationIdentificationNumber;
  private String reporter;
  private String identificationNumber;
  private Problem.State state;
}
