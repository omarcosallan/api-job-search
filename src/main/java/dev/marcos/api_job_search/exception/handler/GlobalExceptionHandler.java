package dev.marcos.api_job_search.exception.handler;

import dev.marcos.api_job_search.dto.error.ProblemDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Internal server error",
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                getRequestPath(request));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }

    private String getRequestPath(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
