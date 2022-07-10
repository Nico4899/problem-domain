package edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions;

public class NotFoundException extends RuntimeException {

  private final static String NOT_FOUND_MESSAGE = "Resource not found. Maybe your Request was wrong?";

  public NotFoundException() {
    super(NOT_FOUND_MESSAGE);
  }

}
