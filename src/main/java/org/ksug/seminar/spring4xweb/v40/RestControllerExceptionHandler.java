package org.ksug.seminar.spring4xweb.v40;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link RestController} 한정판!
 * 
 * @author honeymon
 *
 */
@ControllerAdvice(annotations = { RestController.class })
public class RestControllerExceptionHandler {

    @ExceptionHandler(RestControllerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError exception(Exception e, HttpServletRequest request) {
        return new ApiError(request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiError {
        private String url;
        private HttpStatus status;
        private String message;
    }

    public static class RestControllerException extends Exception {
        public RestControllerException(String msg) {
            super(msg);
        }
    }
}
