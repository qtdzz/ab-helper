package com.qtdzz.abhelper;

import java.util.Random;

public class DefaultRandomizer implements ABRandomizer {
  private final Random random = new Random();

  @Override
  public int nextInt(int upperBound) {
    return random.nextInt(upperBound);
  }
  }
