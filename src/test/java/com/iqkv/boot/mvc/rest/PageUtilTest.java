/*
 * Copyright 2024 IQKV.
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

package com.iqkv.boot.mvc.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

class PageUtilTest {

  private static final int PAGE_SIZE = 20;
  private static final int TOTAL_ELEMENTS_OF_3 = 3;
  private static final int TOTAL_ELEMENTS_OF_40 = 40;
  private static final int TOTAL_PAGES_OF_1 = 1;
  private static final int TOTAL_PAGES_OF_2 = 2;

  private List<Integer> content;

  @BeforeEach
  void setup() {
    content = new ArrayList<>();
  }

  @Test
  void generatePageFromListTestShouldCreatePage() {
    content.add(1);
    content.add(2);
    content.add(3);

    Page<Integer> page = PageUtil.createPageFromList(content, PageRequest.of(0, PAGE_SIZE));

    assertThat(page).isNotNull();
    assertThat(page.getSize()).isEqualTo(PAGE_SIZE);
    assertThat(page.getTotalElements()).isEqualTo(TOTAL_ELEMENTS_OF_3);
    assertThat(page.getTotalPages()).isEqualTo(TOTAL_PAGES_OF_1);
  }

  @Test
  void generatePageFromListShouldCreatePageWithTwoTotalPages() {
    for (int i = 0; i < TOTAL_ELEMENTS_OF_40; i++) {
      content.add(i);
    }

    Page<Integer> page = PageUtil.createPageFromList(content, PageRequest.of(0, PAGE_SIZE));

    assertThat(page).isNotNull();
    assertThat(page.getSize()).isEqualTo(PAGE_SIZE);
    assertThat(page.getTotalElements()).isEqualTo(TOTAL_ELEMENTS_OF_40);
    assertThat(page.getTotalPages()).isEqualTo(TOTAL_PAGES_OF_2);
  }

  @Test
  void generatePageFromListShouldThrowIllegalArgumentExceptionIfListNull() {
    assertThatThrownBy(() -> PageUtil.createPageFromList(null, PageRequest.of(0, PAGE_SIZE))).isInstanceOf(
        IllegalArgumentException.class
    );
  }
}
