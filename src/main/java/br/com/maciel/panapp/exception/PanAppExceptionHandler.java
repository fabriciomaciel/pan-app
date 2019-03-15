package br.com.maciel.panapp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class PanAppExceptionDetails {
  private String message;
  private List<String> details;

  public PanAppExceptionDetails(String msg, List<String> details) {
    this.message = msg;
    this.details = details;
  }

  public String getMessage() {    return message;  }
  public void setMessage(String message) {    this.message = message;  }
  public List<String> getDetails() {    return details;  }
  public void setDetails(List<String> details) {    this.details = details;  }
}

@ControllerAdvice
public class PanAppExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handle(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }

  @ExceptionHandler(PanAppException.class)
  public ResponseEntity<?> handlePanApp(PanAppException e) {
    return new ResponseEntity<>(new PanAppExceptionDetails(e.getErrorMessage(), e.getErrorDetails()), e.getErrorStatus());
  }
}
