package com.consumer.transaction.calc;

import org.springframework.stereotype.Service;

@Service
public class ValueCalculator {

  static final double THRESHOLD = 0.0001d;

  public Double calculate(Double price, Integer quantity) throws ValueCalculationException {

    if (price.compareTo(0.0) < 0) {
      throw new ValueCalculationException("Price could not be negative");
    }

    if (price.compareTo(0.0) == 0 || quantity == 0) {
      return 0.0;
    }

    return price * quantity;
  }
}
