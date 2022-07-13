package edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions;

public class NoSuchElementFoundException extends RuntimeException {

  private static final String NOT_FOUND_MESSAGE = "Resource not found. Maybe your Request was wrong?";

  public NoSuchElementFoundException() {
    super(NOT_FOUND_MESSAGE);
  }

}
