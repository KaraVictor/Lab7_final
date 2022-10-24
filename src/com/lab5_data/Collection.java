package com.lab5_data;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Vector;

public class Collection {
    public final LinkedHashMap<String, Movie> collection;
    public final String primitiveTime;
    public int id = 0;

    public Collection() {
        collection = new LinkedHashMap<>();
        primitiveTime = new Date().toString();
    }

}