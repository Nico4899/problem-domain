package edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions;

public class InternalServerErrorException extends RuntimeException {

  private static final String SERVER_ERROR_MESSAGE = "An internal Server Error occurred. Please try again!";

  public InternalServerErrorException() {
    super(SERVER_ERROR_MESSAGE);
  }
}
