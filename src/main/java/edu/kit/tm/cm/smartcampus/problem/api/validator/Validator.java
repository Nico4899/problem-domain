package edu.kit.tm.cm.smartcampus.problem.api.validator;

import org.springframework.data.util.Pair;

import java.util.Collection;
import java.util.Optional;

/**
 * Class representing a validator which checks given inputs and thereby validates them.
 */
public final class Validator {

  private Validator() {
  }

  //TODO Datumsformate prüfen?

  /**
   * Validates weather objects are not null or not.
   *
   * @param objects objects to be checked and their names (<object, name>)
   * @return an Optional object which holds a String if a null object was found
   */
  public static Optional<String> validateNotNull(Collection<Pair<Object, String>> objects) {
    for (Pair<Object, String> p : objects) {
      if (p.getFirst() == null) {
        return Optional.of(p.getSecond() + " is null");
      }
    }
    return Optional.empty();
  }

  /**
   * Validates weather Strings are not empty or not.
   *
   * @param strings strings to be checked and their names (<string, name>)
   * @return an Optional object which holds a String if an empty object was found
   */
  public static Optional<String> validateNotEmpty(Collection<Pair<String, String>> strings) {
    for (Pair<String, String> p : strings) {
      if (p.getFirst().isEmpty()) {
        return Optional.of(p.getSecond() + " is empty");
      }
    }
    return Optional.empty();
  }

  /**
   * Validates weather Strings match given regexes or not.
   *
   * @param strings strings and their regexes to be checked and their names (<<string,regex>, name>)
   * @return an Optional object which holds a String if a string did not match its regex
   */
  public static Optional<String> validateMatchesRegex(Collection<Pair<Pair<String, String>, String>> strings) {
    for (Pair<Pair<String, String>, String> p : strings) {
      if (!p.getFirst().getFirst().matches(p.getFirst().getSecond())) {
        return Optional.of(p.getSecond() + "does not match the regex " + p.getFirst().getSecond() + " ");
      }
    }
    return Optional.empty();
  }

}
