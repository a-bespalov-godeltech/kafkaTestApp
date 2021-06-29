package com.consumer.transaction.calc;

import static com.consumer.transaction.calc.ValueCalculator.THRESHOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ValueCalculatorTest {

  private final ValueCalculator calculator = new ValueCalculator();

  @Test
  @SneakyThrows
  void negativePrice_ShouldThrowException() {

    val exception = assertThrows(ValueCalculationException.class, () -> calculator.calculate(-1.0, 100));
    assertThat(exception.getMessage()).isEqualTo("Price could not be negative");
  }

  @Test
  @SneakyThrows
  void zeroPriceOrQuantity_ShouldCalculateZero() {

    assertEquals(calculator.calculate(0.0, 100), 0.0d);
    assertEquals(calculator.calculate(1.2, 0), 0.0d);
  }

  @Test
  @SneakyThrows
  void correctPrice_ShouldCalculateCorrectValue() {

    assertEquals(calculator.calculate(1.0, 100), 100.0d);

    assertEquals(calculator.calculate(1.5, 10), 15.0d);
  }

  private void assertEquals(double expected, double actual) {
    Assertions.assertEquals(expected, actual, THRESHOLD);
  }
}
