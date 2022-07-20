package edu.kit.tm.cm.smartcampus.problem.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class representing a problem input validator which checks given objects from the problem model and thereby
 * validates them and throws the right exceptions when an input is invalid.
 */
@Component
@AllArgsConstructor
public class ProblemInputValidator extends InputValidator {

  /**
   * Validates a given Problem (regarding everything that has nothing to do with the database) using the InputValidator
   *
   * @param problem the problem to be validated
   */
  public void validate(Problem problem) { //TODO validate creation time
    validateNotNull(Map.of(
            "problem", problem,
            "problem title", problem.getProblemTitle(),
            "problem description", problem.getProblemDescription(),
            "problem identification number", problem.getPin(),
            "problem reference identification number", problem.getReferenceIn(),
            "problem notification identification number", problem.getNin(),
            "problem state", problem.getProblemState(),
            "problem reporter", problem.getProblemReporter(),
            "problem creation time attribute", problem.getCreationTime(),
            "problem creation time", problem.getCreationTime().getTime()));

    validateNotEmpty(Map.of(
            "problem title", problem.getProblemTitle(),
            "problem description", problem.getProblemDescription(),
            "problem reporter", problem.getProblemReporter()));

    validateMatchesRegex(Map.of(
            "problem identification number", Pair.of(problem.getPin(), "PIN_PATTERN"),//TODO
            "problem reference identification number", Pair.of(problem.getReferenceIn(), "REFERENCE_IN_PATTERN"),
            "problem notification identification number",
            Pair.of(problem.getNin(), "NIN_PATTERN")));
  }

  /**
   * Validates an identification number regarding everything that has nothing to do with the database.
   *
   * @param in      the identification number to be validated
   * @param inName  the name of the identification number
   * @param inRegex the regex that the identification number should match
   */
  public void validateIn(String in, String inName, String inRegex) {
    validateNotNull(Map.of(inName, in));
    validateNotNull(Map.of(inName, Pair.of(in, inRegex)));
  }

}
