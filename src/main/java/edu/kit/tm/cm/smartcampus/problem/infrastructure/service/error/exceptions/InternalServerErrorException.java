package edu.kit.tm.cm.smartcampus.problem.infrastructure.service.error.exceptions;

/**
 * This exception is thrown whenever some internal server error is captured, it contains a proper
 * error message.
 *
 * @author Jonathan Kramer, Johannes von Geisau
 */
public class InternalServerErrorException extends RuntimeException {

  private static final String SERVER_ERROR_MESSAGE =
      "An internal Server Error occurred. Please try again!";

  /**
   * Constructs a new {@link InternalServerErrorException}.
   */
  public InternalServerErrorException() {
    super(SERVER_ERROR_MESSAGE);
  }
}
