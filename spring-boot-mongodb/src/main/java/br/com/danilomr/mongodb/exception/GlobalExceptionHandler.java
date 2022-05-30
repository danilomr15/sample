package br.com.danilomr.mongodb.exception;

import br.com.danilomr.mongodb.controller.dto.DefaultErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static br.com.danilomr.mongodb.utils.Constants.ERROR_DEFAULT_MESSAGE;
import static br.com.danilomr.mongodb.utils.Constants.INVALID_FIELD;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    ResponseEntity<DefaultErrorDTO> handle(final APIException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(buildError(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<DefaultErrorDTO> handle(final MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildError(String.format(INVALID_FIELD, ex.getName())));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<DefaultErrorDTO> handle(final Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildError(ERROR_DEFAULT_MESSAGE));
    }

    private DefaultErrorDTO buildError(final String message) {
        return DefaultErrorDTO.builder()
                .message(message)
                .build();
    }
}
