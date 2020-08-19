package com.github.syed.bugtracker.integration.steps;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    private static final Map<String, Object> map = new HashMap<>();

    public static void put(String id, Object object){
        map.put(id, object);
    }

    public static Object get(String id){
        return map.getOrDefault(id, null);
    }
}
