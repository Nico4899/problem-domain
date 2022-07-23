package edu.kit.tm.cm.smartcampus.problem.logic.model;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.PrefixSequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * This class represents a domain entity problem, it holds {@link State} as public enum constants.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = Problem.PROBLEM_TABLE_NAME)
public class Problem {

  /** The constant PROBLEM_TABLE_NAME. */
  // table name (must be public, else annotation can't find it)
  public static final String PROBLEM_TABLE_NAME = "problem";

  // constants this class uses
  private static final String REFERENCE_IDENTIFICATION_NUMBER_COLUMN =
      "reference_identification_number";
  private static final String NOTIFICATION_IDENTIFICATION_NUMBER_COLUMN =
      "notification_identification_number";
  private static final String CREATION_TIME_COLUMN = "creation_time";
  private static final String PROBLEM_SEQUENCE_NAME = "problem_sequence";
  private static final String GENERATOR_PATH =
      "edu/kit/tm/cm/smartcampus/building/infrastructure/database/PrefixSequenceGenerator.java";
  private static final String PROBLEM_IDENTIFICATION_NUMBER_PREFIX = "p-";
  private static final String IDENTIFICATION_NUMBER_COLUMN = "identification_number";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROBLEM_SEQUENCE_NAME)
  @SequenceGenerator(name = PROBLEM_SEQUENCE_NAME, allocationSize = 1)
  @GenericGenerator(
      name = PROBLEM_SEQUENCE_NAME,
      strategy = GENERATOR_PATH,
      parameters = {
        @Parameter(
            name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER,
            value = PROBLEM_IDENTIFICATION_NUMBER_PREFIX)
      })
  @Column(name = IDENTIFICATION_NUMBER_COLUMN)
  private String identificationNumber;

  @Column(name = REFERENCE_IDENTIFICATION_NUMBER_COLUMN)
  private String referenceIdentificationNumber;

  @Column(name = NOTIFICATION_IDENTIFICATION_NUMBER_COLUMN)
  private String notificationIdentificationNumber;

  @Column(name = CREATION_TIME_COLUMN)
  private Timestamp creationTime;

  private String title;
  private String description;
  private State state;
  private String reporter;

  /** This enum describes the possible states of a problem */
  public enum State {
    /** Open state. */
    OPEN,
    /** Accepted state. */
    ACCEPTED,
    /** In progress state. */
    IN_PROGRESS,
    /** Declined state. */
    DECLINED,
    /** Closed state. */
    CLOSED
  }
}
