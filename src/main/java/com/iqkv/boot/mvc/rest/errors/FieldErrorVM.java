/*
 * Copyright 2025 IQKV Team, and the original author or authors from the JHipster project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.boot.mvc.rest.errors;

import java.io.Serial;
import java.io.Serializable;

public class FieldErrorVM implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String objectName;

  private final String field;

  private final String message;

  public FieldErrorVM(String dto, String field, String message) {
    this.objectName = dto;
    this.field = field;
    this.message = message;
  }

  public String getObjectName() {
    return objectName;
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }
}
