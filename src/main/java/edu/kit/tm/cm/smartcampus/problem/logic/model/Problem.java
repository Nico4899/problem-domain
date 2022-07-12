package edu.kit.tm.cm.smartcampus.problem.logic.model;

import com.sun.istack.NotNull;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.PrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Problem")
public class Problem {

  @NonNull
  private String refId;

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
  @Column(
          nullable = false,
          updatable = false,
          columnDefinition = "TEXT")
  private String id;

  @NonNull
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
