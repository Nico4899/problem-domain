package edu.kit.tm.cm.smartcampus.problem.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request for a Problem.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemRequest {

  private String problemTitle;

  private String problemDescription;

  private String referenceIn;

  private String nin;

  private String problemReporter;


}
