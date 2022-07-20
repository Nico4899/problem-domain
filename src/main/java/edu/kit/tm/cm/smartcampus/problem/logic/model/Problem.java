package edu.kit.tm.cm.smartcampus.problem.logic.model;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.PrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "problem")
public class Problem {

  @Column(name = "problem_title")
  private String problemTitle;

  @Column(name = "problem_description")
  private String problemDescription;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_sequence")
  @SequenceGenerator(name = "problem_sequence", allocationSize = 1)
  @GenericGenerator(
          name = "problem_sequence",
          strategy =
                  "edu/kit/tm/cm/smartcampus/building/infrastructure/database/PrefixSequenceGenerator.java",
          parameters = {
                  @org.hibernate.annotations.Parameter(
                          name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
                          value = "p-")
          })
  private String pin;

  private String referenceIn;

  private String nin;

  @Column(name = "problem_state")
  private ProblemState problemState;

  @Column(name = "problem_reporter")
  private String problemReporter;

  @Column(name = "creation_time")
  private Timestamp creationTime;

}
