package com.lab5_data;

import java.io.Serializable;

public enum MpaaRating implements Serializable {

    G,
    PG,
    PG_13,
    R;

    public static boolean contains(String test) {
        for (MpaaRating rating : MpaaRating.values()) {
            if (rating.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}