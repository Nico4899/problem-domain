package edu.kit.tm.cm.smartcampus.problem.infrastructure.service.error.exceptions;

/**
 * This exception is thrown whenever invalid arguments are found, it contains a proper error
 * message.
 * @author Jonathan Kramer, Johannes von Geisau
 */
public class InvalidArgumentsException extends RuntimeException {

  private static final String INVALID_ARGUMENTS_EXCEPTION_MESSAGE =
      "Arguments are Invalid! Please check the following: %s";

  /**
   * Constructs a new {@link InvalidArgumentsException}.
   *
   * @param message the message provided
   */
  public InvalidArgumentsException(String message) {
    super(String.format(INVALID_ARGUMENTS_EXCEPTION_MESSAGE, message));
  }
}
