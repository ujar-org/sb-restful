package org.ujar.boot.restful.web;

import java.time.LocalDateTime;

public record ApiError(
    String path,
    String message,
    int statusCode,
    LocalDateTime localDateTime
) {
}
