package edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents an update request for a Problem.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
@Setter
@Getter
@AllArgsConstructor
public class ServerUpdateProblemRequest {

  private String identificationNumber;
  private String title;
  private String description;
  private String referenceIdentificationNumber;
  private String notificationIdentificationNumber;
  private String reporter;
  private State state;
}
