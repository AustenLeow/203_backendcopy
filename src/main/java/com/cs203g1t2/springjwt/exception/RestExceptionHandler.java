package com.cs203g1t2.springjwt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

/**
 * Centralize exception handling in this class.
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Construct a new ResponseEntity to customize the Http error messages
     * This method handles the case in which validation failed for
     * controller method's arguments.
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", status.getReasonPhrase());
        String message = "";
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            message = message + objectError.getDefaultMessage();
        }
        body.put("message", message);
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);

    }

    /**
     * Handle the case in which arguments for controller's methods did not match the
     * type.
     * E.g., bookId passed in is not a number
     */

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleTypeMismatch(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Getter
    public class ApiRequestException extends RuntimeException {
        private final HttpStatus status;

        public ApiRequestException(String message, HttpStatus status) {
            super(message);
            this.status = status;
        }
    }

    @Getter
    public class InputFieldException extends RuntimeException {

        private final BindingResult bindingResult;
        private final Map<String, String> errorsMap;

        public InputFieldException(BindingResult bindingResult) {
            this.bindingResult = bindingResult;
            this.errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            fieldError -> fieldError.getField() + "Error",
                            FieldError::getDefaultMessage));
        }
    }

    public class RoleNotFoundException extends RuntimeException {
        public RoleNotFoundException(String message){
            super(message);
        }
    }
    

}