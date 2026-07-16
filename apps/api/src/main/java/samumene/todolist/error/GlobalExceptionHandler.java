package samumene.todolist.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import samumene.todolist.dto.response.ExceptionResponse;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ExceptionResponse responseError(String message, HttpStatus statusCode) {
        return new ExceptionResponse(
                LocalDateTime.now(),
                statusCode.toString(),
                statusCode.value(),
                message
        );
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> noSuchElement(NoSuchElementException i) {
        ExceptionResponse erro = this.responseError(i.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgument(IllegalArgumentException i) {
        ExceptionResponse erro = this.responseError(i.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValid(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                this.responseError(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    // Security

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> authNotFound() {
        return new ResponseEntity<>(this.responseError("Não autenticado", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ExceptionResponse> InsufficientAuth() {
        return new ResponseEntity<>(this.responseError("Autenticação insuficiente", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> authNotSupported() {
        return new ResponseEntity<>(this.responseError("Autenticação inválida", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> auth() {
        return new ResponseEntity<>(this.responseError("Não permitido", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDenied() {
        return new ResponseEntity<>(this.responseError("Acesso negado", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }



}
