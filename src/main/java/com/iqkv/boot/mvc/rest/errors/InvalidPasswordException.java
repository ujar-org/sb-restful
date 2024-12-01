package com.iqkv.boot.mvc.rest.errors;

import java.io.Serial;

import com.iqkv.boot.mvc.rest.errors.ProblemDetailWithCause.ProblemDetailWithCauseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class InvalidPasswordException extends ErrorResponseException {

  @Serial
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
