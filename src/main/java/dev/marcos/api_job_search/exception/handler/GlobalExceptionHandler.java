package dev.marcos.api_job_search.exception.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import dev.marcos.api_job_search.dto.error.ProblemDetail;
import dev.marcos.api_job_search.exception.ConflictException;
import dev.marcos.api_job_search.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ProblemDetail> handleAuthenticationException(
            AuthenticationException ex, HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Authentication error",
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                getRequestPath(request));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problem);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentialsException(HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Authentication failed",
                "Invalid username or password",
                HttpStatus.UNAUTHORIZED.value(),
                getRequestPath(request));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problem);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAuthorizationDeniedException(HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Forbidden",
                "You don't have permission to access this resource",
                HttpStatus.FORBIDDEN.value(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problem);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String detail =
                String.format(
                        "Parameter '%s' has invalid value '%s'. Expected type: %s",
                        ex.getName(),
                        ex.getValue(),
                        ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");

        ProblemDetail problem = new ProblemDetail(
                "Invalid parameter",
                detail,
                HttpStatus.BAD_REQUEST.value(),
                getRequestPath(request));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        String detail = "Request body is invalid or malformed";

        if (ex.getCause() != null) {
            detail += ": " + ex.getCause().getMessage();
        }

        ProblemDetail problem = new ProblemDetail(
                "Malformed JSON request",
                detail,
                HttpStatus.BAD_REQUEST.value(),
                getRequestPath(request));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler({
            SignatureVerificationException.class,
            JWTVerificationException.class,
            JWTCreationException.class
    })
    public ResponseEntity<ProblemDetail> handleTokenException(HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Invalid token",
                "The required authentication information is invalid. The token is either invalid or has expired",
                HttpStatus.UNAUTHORIZED.value(),
                getRequestPath(request)
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problem);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFound(
            NotFoundException ex, HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Resource not found",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                getRequestPath(request)
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ProblemDetail> handleConflictException(
            ConflictException ex, HttpServletRequest request) {

        ProblemDetail problem = new ProblemDetail(
                "Conflict error",
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                getRequestPath(request)
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
    }

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
