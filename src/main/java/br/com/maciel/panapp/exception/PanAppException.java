package br.com.maciel.panapp.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class PanAppException extends RuntimeException {

  /** The error status. */
  private HttpStatus errorStatus;
  /** The error message. */
  private String errorMessage;
  /** The lista erros. */
  private List<String> errorDetails = new ArrayList<String>();


  /**
   * Instantiates a new PanApp exception.
   * @param errorStatusParam
   *            the error status param
   * @param errorMessageParam
   *            the error message param
   * @param errorDetailsParam
   *            the error details param
   */
  public PanAppException(HttpStatus errorStatusParam,
                           String errorMessageParam,
                           List<String> errorDetailsParam) {
    this.errorStatus = errorStatusParam;
    this.errorMessage = errorMessageParam;
    this.errorDetails.addAll(errorDetailsParam);
  }

  /**
   * Gets the error message.
   *
   * @return the errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Gets the error details.
   *
   * @return the errorDetails
   */
  public List<String> getErrorDetails() {
    return errorDetails;
  }

  /**
   * Gets the error status.
   *
   * @return the errorStatus
   */
  public HttpStatus getErrorStatus() {
    return errorStatus;
  }
}