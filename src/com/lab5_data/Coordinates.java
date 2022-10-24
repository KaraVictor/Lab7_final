package com.lab5_data;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final Double x; // Field cannot be null
    private final long y;

    // class constructor

    public Coordinates(Double x, long y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}
