package edu.kit.tm.cm.smartcampus.problem.api.error;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InternalServerErrorException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.UnauthorizedAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestServerErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidArgumentsException.class)
  protected ResponseEntity<Object> handleInvalidArgumentsException(
      InvalidArgumentsException exception, WebRequest request) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<Object> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest request) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  protected ResponseEntity<Object> handleInternalServerErrorException(
      InternalServerErrorException exception, WebRequest request) {
    return handleExceptionInternal(
        exception,
        exception.getMessage(),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }

  @ExceptionHandler(UnauthorizedAccessException.class)
  protected ResponseEntity<Object> handleUnauthorizedAccessException(
      UnauthorizedAccessException exception, WebRequest request) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }
}
