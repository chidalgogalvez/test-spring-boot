package cl.heroes.client.exception.handler;


import cl.heroes.client.exception.BadRequestException;
import cl.heroes.client.exception.ConflictException;
import cl.heroes.client.exception.ErrorResponse;
import cl.heroes.client.exception.NotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Argument not valid",
                "BAD_REQUEST",
                errors,
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Throwable mostSpecificCause = ex.getMostSpecificCause();
        ErrorResponse message = new ErrorResponse(
                status.value(),
                new Date(),
                "Argument not valid",
                "BAD_REQUEST",
                mostSpecificCause != null ? Arrays.asList(mostSpecificCause.getMessage()) : Arrays.asList(ex.getMessage()),
                request.getDescription(false));

        log.error(mostSpecificCause.getClass().getName());
        return new ResponseEntity(message, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ErrorResponse message = new ErrorResponse(
                status.value(),
                new Date(),
                "This exception is thrown when the request is missing a parameter",
                "BAD_REQUEST",
                ex.getLocalizedMessage(),
                request.getDescription(false));

        return new ResponseEntity(message, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ErrorResponse message = new ErrorResponse(
                status.value(),
                new Date(),
                "This exception is thrown when method argument is not the expected type",
                "BAD_REQUEST",
                ex.getLocalizedMessage(),
                request.getDescription(false));

        return new ResponseEntity(message, headers, status);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                "RESOURCE_NOT_FOUND",
                request.getDescription(false));
        log.error("Resource not found: " + ex.getLocalizedMessage());
        ResponseEntity<ErrorResponse> errorResponse = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return errorResponse;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(BadRequestException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                "BAD_REQUEST",
                request.getDescription(false));
        log.error("Input data error: " + ex.getLocalizedMessage());
        ResponseEntity<ErrorResponse> errorResponse = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        return errorResponse;
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> conflictException(ConflictException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                "CONFLICT",
                request.getDescription(false));
        log.error("Conflict: " + ex.getLocalizedMessage());
        ResponseEntity<ErrorResponse> errorResponse = new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                "INTERNAL_SERVER_ERROR",
                request.getDescription(false));
        ResponseEntity<ErrorResponse> errorResponse = new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        return errorResponse;
    }
}
