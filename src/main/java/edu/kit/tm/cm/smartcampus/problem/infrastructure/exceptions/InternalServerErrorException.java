package edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions;

public class InternalServerErrorException extends RuntimeException {

  private final static String SERVER_ERROR_MESSAGE = "An internal Server Error occurred.";

  public InternalServerErrorException() {
    super(SERVER_ERROR_MESSAGE);
  }
}
