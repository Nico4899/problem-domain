package edu.kit.tm.cm.smartcampus.problem.api.error;

import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InternalServerErrorException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.InvalidArgumentsException;
import edu.kit.tm.cm.smartcampus.problem.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class represents a server exception interceptor, it intercepts on exceptions annotated with
 * {@link ExceptionHandler} with proper {@link HttpStatus} and the exception message. The {@link
 * ControllerAdvice} annotation makes this interceptor global this removes the need of other
 * exception handling.
 */
@ControllerAdvice
public class ServerExceptionInterceptor extends ResponseEntityExceptionHandler {

  /**
   * This method provides a proper response on {@link InvalidArgumentsException} thrown, it provides
   * a {@link HttpStatus#BAD_REQUEST} and the exception message.
   *
   * @param exception thrown exception
   * @param request the caught web request
   * @return a proper {@link ResponseEntity}
   */
  @ExceptionHandler(InvalidArgumentsException.class)
  protected ResponseEntity<Object> onError(
      InvalidArgumentsException exception, WebRequest request) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  /**
   * This method provides a proper response on {@link ResourceNotFoundException} thrown, it provides
   * a {@link HttpStatus#NOT_FOUND} and the exception message.
   *
   * @param exception thrown exception
   * @param request the caught web request
   * @return a proper {@link ResponseEntity}
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<Object> onError(
      ResourceNotFoundException exception, WebRequest request) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  /**
   * This method provides a proper response on {@link InternalServerErrorException} thrown, it
   * provides a {@link HttpStatus#INTERNAL_SERVER_ERROR} and the exception message.
   *
   * @param exception thrown exception
   * @param request the caught web request
   * @return a proper {@link ResponseEntity}
   */
  @ExceptionHandler(InternalServerErrorException.class)
  protected ResponseEntity<Object> onError(
      InternalServerErrorException exception, WebRequest request) {
    return handleExceptionInternal(
        exception,
        exception.getMessage(),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }

  /**
   * This method provides a proper response on any other {@link Exception} thrown, it provides a
   * {@link HttpStatus#I_AM_A_TEAPOT} and the exception message.
   *
   * @param exception thrown exception
   * @param request the caught web request
   * @return a proper {@link ResponseEntity}
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> onError(Exception exception, WebRequest request) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
  }
}
