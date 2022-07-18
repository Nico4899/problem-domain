package edu.kit.tm.cm.smartcampus.problem.api.validator;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class representing a validator which checks given inputs and thereby validates them.
 */
@Component
public class Validator {

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects Map of objects to be checked and their names (key=name, value=object)
   * @throws NullPointerException if one of the objects is null
   */
  public void validateNotNull(Map<String, Object> objects) throws NullPointerException {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Object> entry : objects.entrySet()) {
      if (entry.getValue() == null) {
        invalidArgumentsException.appendWrongArguments(entry.getKey(), "is null", "", false);
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
   * @throws InvalidArgumentsException if an empty object was found
   */
  public void validateNotEmpty(Map<String, String> strings) throws InvalidArgumentsException {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, String> entry : strings.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        invalidArgumentsException.appendWrongArguments(entry.getKey(), "is empty", "", false);
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
   * @param strings Map of strings and their regexes to be checked and their names (key=name, value=pair of string
   *                and regex)
   * @throws InvalidArgumentsException if a string did not match its regex
   */
  public void validateMatchesRegex(Map<String, Pair<String, String>> strings)
      throws InvalidArgumentsException {
    InvalidArgumentsException invalidArgumentsException = new InvalidArgumentsException();
    boolean valid = true;

    for (Map.Entry<String, Pair<String, String>> entry : strings.entrySet()) {
      if (!entry.getValue().getFirst().matches(entry.getValue().getSecond())) {
        invalidArgumentsException.appendWrongArguments(entry.getKey(), "does not have the right format",
            "should match: " + entry.getValue().getSecond(), true);
        valid = false;
      }
    }

    if (!valid) {
      throw invalidArgumentsException;
    }
  }

}
