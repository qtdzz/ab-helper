package com.qtdzz.abhelper;

import java.util.Random;

public class ABStrategy {
    public int getVariant(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }
}
