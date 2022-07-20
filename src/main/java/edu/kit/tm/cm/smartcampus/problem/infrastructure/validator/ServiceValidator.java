package edu.kit.tm.cm.smartcampus.problem.infrastructure.validator;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.database.ProblemRepository;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import static edu.kit.tm.cm.smartcampus.problem.utils.*;

@Component
public final class ServiceValidator {


  private final ProblemRepository problemRepository;

  public ServiceValidator(ProblemRepository problemRepository){
    this.problemRepository = problemRepository;
  }

  public void validateInIsMapped(CrudRepository repository, String in) throws ResourceNotFoundException {
    if(repository.findById(in).isEmpty()) {
      throw new ResourceNotFoundException(String.format(NOT_FOUND, in));
    }
  }

  public void validateInDoesNotExist(CrudRepository repository, String in) {
    InvalidArgumentsException inArgsEx = new InvalidArgumentsException();
    if(repository.existsById(in)) {
      inArgsEx.appendWrongArguments(IN, in, RESOURCE_ALREADY_EXISTS, true);
      throw inArgsEx;
    }
  }



}
