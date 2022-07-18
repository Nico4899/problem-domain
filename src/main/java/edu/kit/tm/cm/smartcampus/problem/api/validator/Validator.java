package edu.kit.tm.cm.smartcampus.problem.api.validator;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Class representing a validator which checks given inputs and thereby validates them.
 */
@Component
public class Validator {

  //TODO Datumsformate prüfen?

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects objects to be checked and their names (<object, name>)
   * @throws NullPointerException if one of the objects is null
   */
  public void validateNotNull(Collection<Pair<Object, String>> objects) throws NullPointerException {
    for (Pair<Object, String> p : objects) {
      if (p.getFirst() == null) {
        throw new NullPointerException(p.getSecond() + " is null");
      }
    }
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings strings to be checked and their names (<string, name>)
   * @throws InvalidArgumentsException if an empty object was found
   */
  public void validateNotEmpty(Collection<Pair<String, String>> strings) throws InvalidArgumentsException {
    for (Pair<String, String> p : strings) {
      if (p.getFirst().isEmpty()) {
        throw new InvalidArgumentsException(p.getSecond(), "", "should not be empty", true);
      }
    }
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings strings and their regexes to be checked and their names (<<string,regex>, name>)
   * @throws InvalidArgumentsException if a string did not match its regex
   */
  public void validateMatchesRegex(Collection<Pair<Pair<String, String>, String>> strings)
      throws InvalidArgumentsException {
    for (Pair<Pair<String, String>, String> p : strings) {
      if (!p.getFirst().getFirst().matches(p.getFirst().getSecond())) {
        throw new InvalidArgumentsException(p.getSecond() + " ", p.getFirst().getFirst(),
            " does not match the regex " + p.getFirst().getSecond(), true);
      }
    }
  }

}
