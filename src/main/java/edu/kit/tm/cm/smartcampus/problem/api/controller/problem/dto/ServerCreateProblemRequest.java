package edu.kit.tm.cm.smartcampus.problem.api.controller.problem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** This class represents a request for a Problem. */
@Setter
@Getter
@AllArgsConstructor
public class ServerCreateProblemRequest {
  private String title;
  private String description;
  private String referenceIdentificationNumber;
  private String reporter;
}
