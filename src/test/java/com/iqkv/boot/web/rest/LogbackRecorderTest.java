package com.iqkv.boot.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

import java.util.List;

import com.iqkv.boot.web.rest.LogbackRecorder.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

class LogbackRecorderTest {

  private static final String[] TEST_MESSAGES = {"error", "warn", "info", "debug", "trace"};
  private static final Object[] TEST_ARGUMENTS = {null, true, 1, 2D, 3F};

  private static final Logger LOG = LoggerFactory.getLogger(LogbackRecorderTest.class);
  private static final Marker marker = MarkerFactory.getMarker(LOG.getName());

  private final Exception exception = new RuntimeException("Eek");

  private LogbackRecorder recorder = LogbackRecorder.forLogger(log);

  @BeforeEach
  void setup() {
    recorder.reset();
  }

  @Test
  void testTrace() {
    recorder.capture("TRACE");

    write();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(5);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isNull();
    }

    recorder.release();
  }

  @Test
  void testTraceWithException() {
    recorder.capture("TRACE");

    writeWithException();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(5);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isEqualTo(exception.toString());
    }

    recorder.release();
  }

  @Test
  void testDebug() {
    recorder.capture("DEBUG");

    write();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(4);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isNull();
    }

    recorder.release();
  }

  @Test
  void testDebugWithException() {
    recorder.capture("DEBUG");

    writeWithException();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(4);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isEqualTo(exception.toString());
    }

    recorder.release();
  }

  @Test
  void testInfo() {
    recorder.capture("INFO");

    write();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(3);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isNull();
    }

    recorder.release();
  }

  @Test
  void testInfoWithException() {
    recorder.capture("INFO");

    writeWithException();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(3);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isEqualTo(exception.toString());
    }

    recorder.release();
  }

  @Test
  void testWarn() {
    recorder.capture("WARN");

    write();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(2);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isNull();
    }

    recorder.release();
  }

  @Test
  void testWarnWithException() {
    recorder.capture("WARN");

    writeWithException();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(2);

    for (int i = 0; i < events.size(); i++) {
      Event event = events.get(i);
      assertThat(event.getMarker()).isEqualTo(marker);
      assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[i].toUpperCase());
      assertThat(event.getMessage()).startsWith(TEST_MESSAGES[i]);
      assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[i]);
      assertThat(event.getThrown()).isEqualTo(exception.toString());
    }

    recorder.release();
  }

  @Test
  void testError() {
    recorder.capture("ERROR");

    write();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(1);

    Event event = events.get(0);
    assertThat(event.getMarker()).isEqualTo(marker);
    assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[0].toUpperCase());
    assertThat(event.getMessage()).startsWith(TEST_MESSAGES[0]);
    assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[0]);
    assertThat(event.getThrown()).isNull();

    recorder.release();
  }

  @Test
  void testErrorWithException() {
    recorder.capture("ERROR");

    writeWithException();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(1);

    Event event = events.get(0);
    assertThat(event.getMarker()).isEqualTo(marker);
    assertThat(event.getLevel()).isEqualTo(TEST_MESSAGES[0].toUpperCase());
    assertThat(event.getMessage()).startsWith(TEST_MESSAGES[0]);
    assertThat(event.getArguments()).containsExactly(TEST_ARGUMENTS[0]);
    assertThat(event.getThrown()).isEqualTo(exception.toString());

    recorder.release();
  }

  @Test
  void testOff() {
    recorder.capture("OFF");

    write();

    List<Event> events = recorder.play();
    assertThat(events).isEmpty();

    recorder.release();
  }

  @Test
  void testOffWithException() {
    recorder.capture("OFF");

    writeWithException();

    List<Event> events = recorder.play();
    assertThat(events).isEmpty();

    recorder.release();
  }

  @Test
  void testLogbackException() {
    Throwable caught = catchThrowable(() -> LogbackRecorder.forLogger(mock(Logger.class)));
    assertThat(caught).isInstanceOf(IllegalArgumentException.class);
    assertThat(caught).hasMessage(LogbackRecorder.LOGBACK_EXCEPTION_MESSAGE);
  }

  @Test
  void testCaptureException() {
    recorder.capture("ALL");
    Throwable caught = catchThrowable(() -> recorder.capture("ALL"));
    assertThat(caught).isInstanceOf(IllegalStateException.class);
    assertThat(caught).hasMessage(LogbackRecorder.CAPTURE_EXCEPTION_MESSAGE);
    recorder.release();
  }

  @Test
  void testReleaseException() {
    Throwable caught = catchThrowable(() -> recorder.release());
    assertThat(caught).isInstanceOf(IllegalStateException.class);
    assertThat(caught).hasMessage(LogbackRecorder.RELEASE_EXCEPTION_MESSAGE);
  }

  @Test
  void testClear() {
    recorder.capture("TRACE");

    write();

    recorder.release();

    assertThat(recorder.play()).hasSize(5);
    recorder.reset();
    assertThat(recorder.play()).isEmpty();
  }

  private void write() {
    LOG.error(marker, TEST_MESSAGES[0] + " {}", TEST_ARGUMENTS[0]);
    LOG.warn(marker, TEST_MESSAGES[1] + " {}", TEST_ARGUMENTS[1]);
    LOG.info(marker, TEST_MESSAGES[2] + " {}", TEST_ARGUMENTS[2]);
    LOG.debug(marker, TEST_MESSAGES[3] + " {}", TEST_ARGUMENTS[3]);
    LOG.trace(marker, TEST_MESSAGES[4] + " {}", TEST_ARGUMENTS[4]);
  }

  private void writeWithException() {
    LOG.error(marker, TEST_MESSAGES[0] + " {}", TEST_ARGUMENTS[0], exception);
    LOG.warn(marker, TEST_MESSAGES[1] + " {}", TEST_ARGUMENTS[1], exception);
    LOG.info(marker, TEST_MESSAGES[2] + " {}", TEST_ARGUMENTS[2], exception);
    LOG.debug(marker, TEST_MESSAGES[3] + " {}", TEST_ARGUMENTS[3], exception);
    LOG.trace(marker, TEST_MESSAGES[4] + " {}", TEST_ARGUMENTS[4], exception);
  }
}
