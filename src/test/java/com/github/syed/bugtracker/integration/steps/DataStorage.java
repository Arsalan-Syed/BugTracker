package com.github.syed.bugtracker.integration.steps;

import com.github.syed.bugtracker.ColorUtils;

import java.awt.*;
import java.lang.reflect.Field;
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

    public static void clear(){
        map.clear();
    }

    public static <T> void setFields(Map<String, String> map, T object) throws NoSuchFieldException, IllegalAccessException {
        for(String key : map.keySet()){
            Class<?> c = object.getClass();
            Field field = c.getDeclaredField(key);
            field.setAccessible(true);

            if(field.getType() == Color.class){
                Color color = ColorUtils.convertToColor(map.get(key));
                field.set(object, color);
            } else{
                field.set(object, map.get(key));
            }

            field.setAccessible(false);
        }
    }
}
