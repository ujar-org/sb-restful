package org.iqkv.boot.web.rest.errors;

import org.iqkv.boot.web.rest.errors.ProblemDetailWithCause.ProblemDetailWithCauseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class InvalidPasswordException extends ErrorResponseException {

  private static final long serialVersionUID = 1L;

  public InvalidPasswordException() {
    super(
        HttpStatus.BAD_REQUEST,
        ProblemDetailWithCauseBuilder.instance()
            .withStatus(HttpStatus.BAD_REQUEST.value())
            .withType(ErrorConstants.INVALID_PASSWORD_TYPE)
            .withTitle("Incorrect password")
            .build(),
        null
    );
  }
}
