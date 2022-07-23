package edu.kit.tm.cm.smartcampus.problem.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.Map;

/**
 * This class represents a parent class validator for any given attribute constraints. In case of
 * invalid arguments, it throws {@link InvalidArgumentsException} and in case of nonexistence of
 * given objects in the database, it throws {@link ResourceNotFoundException}.
 *
 * @param <T> the type of which this validator validates objects
 */
public abstract class Validator<T> {

  /** The constant IDENTIFICATION_NUMBER_NAME. */
  // public constants
  public static final String IDENTIFICATION_NUMBER_NAME = "identification_number";

  /** The constant PIN_PATTERN. */
  public static final String PIN_PATTERN = "^p-\\d+$";

  /** The constant NIN_PATTERN. */
  public static final String NIN_PATTERN = "^n-\\d+$";

  /** The constant RIN_PATTERN. */
  public static final String RIN_PATTERN = "^r-\\d+$";

  /** The constant BIN_PATTERN. */
  public static final String BIN_PATTERN = "^b-\\d+$";

  /** The constant CIN_PATTERN. */
  public static final String CIN_PATTERN = "^c-\\d+$";

  /** The constant BIN_RIN_CIN_PATTERN. */
  public static final String BIN_RIN_CIN_PATTERN =
      BIN_PATTERN + "|" + RIN_PATTERN + "|" + CIN_PATTERN;

  /** The constant PROBLEM_IDENTIFICATION_NUMBER_NAME. */
  public static final String PROBLEM_IDENTIFICATION_NUMBER_NAME = "problem_identification_number";

  /** The constant PROBLEM_NAME. */
  public static final String PROBLEM_NAME = "problem";

  /** The constant TITLE_NAME. */
  public static final String TITLE_NAME = "title";

  /** The constant DESCRIPTION_NAME. */
  public static final String DESCRIPTION_NAME = "description";

  /** The constant REFERENCE_IDENTIFICATION_NUMBER_NAME. */
  public static final String REFERENCE_IDENTIFICATION_NUMBER_NAME =
      "reference_identification_number";

  /** The constant NOTIFICATION_IDENTIFICATION_NUMBER_NAME. */
  public static final String NOTIFICATION_IDENTIFICATION_NUMBER_NAME =
      "notification_identification_number";

  /** The constant STATE_NAME. */
  public static final String STATE_NAME = "state";

  /** The constant REPORTER_NAME. */
  public static final String REPORTER_NAME = "reporter";

  // private constants
  private static final String NULL = "null";
  private static final String SHOULD_NOT_BE_NULL_MESSAGE = "should not be null";
  private static final String SHOULD_NOT_BE_EMPTY_MESSAGE = "should not be empty";
  private static final String SHOULD_MATCH_MESSAGE = "should match: ";
  private final ProblemRepository problemRepository;

  /**
   * Instantiates a new Validator.
   *
   * @param problemRepository the problem repository
   */
  @Autowired
  protected Validator(ProblemRepository problemRepository) {
    this.problemRepository = problemRepository;
  }

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects Map of objects to be checked and their names (key=name, value=object)
   */
  protected void validateNotNull(Map<String, Object> objects) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
        new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Object> entry : objects.entrySet()) {
      if (entry.getValue() == null) {
        invalidArgumentsStringBuilder.appendMessage(
            entry.getKey(), NULL, SHOULD_NOT_BE_NULL_MESSAGE, true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings Map of strings to be checked and their names (key=name, value=string)
   */
  protected void validateNotEmpty(Map<String, String> strings) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
        new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, String> entry : strings.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        invalidArgumentsStringBuilder.appendMessage(
            entry.getKey(), entry.getValue(), SHOULD_NOT_BE_EMPTY_MESSAGE, true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings Map of strings and their regexes to be checked and their names (key=name,
   *     value=pair of string and regex)
   */
  protected void validateMatchesRegex(Map<String, Pair<String, String>> strings) {
    InvalidArgumentsStringBuilder invalidArgumentsStringBuilder =
        new InvalidArgumentsStringBuilder();
    boolean valid = true;

    for (Map.Entry<String, Pair<String, String>> entry : strings.entrySet()) {
      if (!entry.getValue().getFirst().matches(entry.getValue().getSecond())) {
        invalidArgumentsStringBuilder.appendMessage(
            entry.getKey(),
            entry.getValue().getFirst(),
            SHOULD_MATCH_MESSAGE + entry.getValue().getSecond(),
            true);
        valid = false;
      }
    }

    if (!valid) {
      throw new InvalidArgumentsException(invalidArgumentsStringBuilder.build());
    }
  }

  /**
   * Validate if entity exists.
   *
   * @param inputIdentificationNumber the input identification number
   * @param name the name of the given value
   */
  protected void validateExists(String inputIdentificationNumber, String name) {
    if (!problemRepository.existsById(inputIdentificationNumber)) {
      throw new ResourceNotFoundException(name, inputIdentificationNumber);
    }
  }

  /**
   * Validate a given identification number for requests containing only the identification number.
   *
   * @param identificationNumber the identification number
   */
  public void validate(String identificationNumber) {
    validateNotNull(Map.of(IDENTIFICATION_NUMBER_NAME, identificationNumber));
    validateMatchesRegex(
        Map.of(IDENTIFICATION_NUMBER_NAME, Pair.of(identificationNumber, getValidateRegex())));
    validateExists(identificationNumber, IDENTIFICATION_NUMBER_NAME);
  }

  /**
   * Gets validate regex for the {@link Validator#validate(String)} method.
   *
   * @return the validate regex
   */
  protected abstract String getValidateRegex();

  /**
   * Validate create operation.
   *
   * @param object the object to be validated
   */
  public abstract void validateCreate(T object);

  /**
   * Validate update operation.
   *
   * @param object the object to be validated
   */
  public abstract void validateUpdate(T object);

  @NoArgsConstructor
  private static class InvalidArgumentsStringBuilder {

    private static final String COLON = ": ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String COMMA = ", ";

    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Append erro message.
     *
     * @param name the name
     * @param input the input
     * @param hint the hint
     * @param hasHint if a hint is provided
     */
    public void appendMessage(String name, String input, String hint, boolean hasHint) {
      if (stringBuilder.isEmpty()) {
        if (hasHint) {
          appendFirstIterationWithHint(name, input, hint);
        } else {
          appendFirstIterationWithoutHint(name, input);
        }
      } else if (hasHint) {
        appendWithHint(name, input, hint);
      } else {
        appendWithoutHint(name, input);
      }
    }

    /**
     * Build error message string.
     *
     * @return the built string
     */
    public String build() {
      return stringBuilder.toString();
    }

    private void appendWithHint(String name, String input, String hint) {
      stringBuilder
          .append(COMMA)
          .append(name)
          .append(COLON)
          .append(input)
          .append(LEFT_PARENTHESIS)
          .append(hint)
          .append(RIGHT_PARENTHESIS);
    }

    private void appendWithoutHint(String name, String input) {
      stringBuilder.append(COMMA).append(name).append(COLON).append(input);
    }

    private void appendFirstIterationWithoutHint(String name, String input) {
      stringBuilder.append(name).append(COLON).append(input);
    }

    private void appendFirstIterationWithHint(String name, String input, String hint) {
      stringBuilder
          .append(name)
          .append(COLON)
          .append(input)
          .append(LEFT_PARENTHESIS)
          .append(hint)
          .append(RIGHT_PARENTHESIS);
    }
  }
}
