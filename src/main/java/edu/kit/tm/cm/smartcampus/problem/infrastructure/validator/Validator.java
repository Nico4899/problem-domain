package edu.kit.tm.cm.smartcampus.problem.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.data.util.Pair;

import java.util.Map;

/**
 * Class representing an input validator which checks given inputs and thereby validates them and throws the right
 * exceptions when an input is invalid. //TODo javadoc anpassen
 */
public abstract class Validator<T> {

  public static final String VALIDATE_REGEX = "";

  private final ProblemRepository problemRepository;

  protected Validator(ProblemRepository problemRepository) {
    this.problemRepository = problemRepository;
  }

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects Map of objects to be checked and their names (key=name, value=object)
   */
  protected void validateNotNull(Map<String, Object> objects) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Object> entry : objects.entrySet()) {
      if (entry.getValue() == null) {
        invalidArgumentsException.appendWrongArguments(
                entry.getKey(), "null", "should not be null", true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings Map of strings to be checked and their names (key=name, value=string)
   */
  protected void validateNotEmpty(Map<String, String> strings) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, String> entry : strings.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        invalidArgumentsException.appendWrongArguments(
                entry.getKey() + ": ", entry.getValue(), "should not be empty", true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings Map of strings and their regexes to be checked and their names (key=name,
   *                value=pair of string and regex)
   */
  protected void validateMatchesRegex(Map<String, Pair<String, String>> strings) {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Pair<String, String>> entry : strings.entrySet()) {
      if (!entry.getValue().getFirst().matches(entry.getValue().getSecond())) {
        invalidArgumentsException.appendWrongArguments(
                entry.getKey(),
                entry.getValue().getFirst(),
                "should match: " + entry.getValue().getSecond(),
                true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

  protected void validateExists(String inputIdentificationNumber, String name) {
    if (!this.problemRepository.existsById(inputIdentificationNumber)) {
      throw new ResourceNotFoundException(
              String.format("utils.RESOURCE_NOT_FOUND_MESSAGE", name, inputIdentificationNumber)); //TODO
    }
  }

  public void validate(String identificationNumber) {
    validateNotNull(Map.of("identification_number", identificationNumber));
    validateMatchesRegex(Map.of("identification_number", Pair.of(identificationNumber, getValidateRegex())));
    validateExists(identificationNumber, "identification_number");
  }

  protected abstract String getValidateRegex();

  public abstract void validateCreate(T object);

  public abstract void validateUpdate(T object);

}
