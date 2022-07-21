package edu.kit.tm.cm.smartcampus.problem.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProblemValidator extends Validator<Problem> {

  @Autowired
  protected ProblemValidator(ProblemRepository problemRepository) {
    super(problemRepository);
  }

  @Override
  protected String getValidateRegex() {
    return null; //TODO aus der util klasse
  }

  @Override
  public void validateCreate(Problem object) {
    validateNotNull(Map.of(
        "problem", object,
        "problem title", object.getProblemTitle(),
        "problem description", object.getProblemDescription(),
        "problem identification number", object.getPin(),
        "problem reference identification number", object.getReferenceIn(),
        "problem notification identification number", object.getNin(),
        "problem reporter", object.getProblemReporter(), ));

    validateNotEmpty(Map.of(
        "problem title", object.getProblemTitle(),
        "problem description", object.getProblemDescription(),
        "problem reporter", object.getProblemReporter()));

    validateMatchesRegex(Map.of(
        "problem identification number", Pair.of(object.getPin(), "PIN_PATTERN"),
        "problem reference identification number", Pair.of(object.getReferenceIn(), "REFERENCE_IN_PATTERN"),
        "problem notification identification number",
        Pair.of(object.getNin(), "NIN_PATTERN")));
  }

  @Override
  public void validateUpdate(Problem object) {
    validateNotNull(Map.of(
        "problem", object,
        "problem title", object.getProblemTitle(),
        "problem description", object.getProblemDescription(),
        "problem identification number", object.getPin(),
        "problem reference identification number", object.getReferenceIn(),
        "problem notification identification number", object.getNin(),
        "problem state", object.getProblemState(),
        "problem reporter", object.getProblemReporter(),
        "problem creation time attribute", object.getCreationTime(),
        "problem creation time", object.getCreationTime().getTime()));

    validateNotEmpty(Map.of(
        "problem title", object.getProblemTitle(),
        "problem description", object.getProblemDescription(),
        "problem reporter", object.getProblemReporter()));

    validateMatchesRegex(Map.of(
        "problem identification number", Pair.of(object.getPin(), "PIN_PATTERN"),
        "problem reference identification number", Pair.of(object.getReferenceIn(), "REFERENCE_IN_PATTERN"),
        "problem notification identification number",
        Pair.of(object.getNin(), "NIN_PATTERN")));

    validateExists(object.getPin(), "problem_identification_number");
  }

}
