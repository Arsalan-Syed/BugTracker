package com.github.syed.bugtracker.integration.steps;

import com.github.syed.bugtracker.ColorUtils;
import com.github.syed.bugtracker.issue.Priority;
import com.github.syed.bugtracker.user.Name;

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

    public static <T> void setFields(Map<String, String> objectFields, T object) throws NoSuchFieldException, IllegalAccessException {
        for(String objectField : objectFields.keySet()){
            String objectValue = objectFields.get(objectField);
            Class<?> c = object.getClass();
            Field field = c.getDeclaredField(objectField);
            field.setAccessible(true);

            if(field.getType() == Color.class){
                Color color = ColorUtils.convertToColor(objectValue);
                field.set(object, color);
            } else if(field.getType() == Priority.class){
                Priority priority = Priority.valueOf(objectValue);
                field.set(object, priority);
            } else if(field.getType() == Name.class){
                String firstName = objectValue.split(" ")[0];
                String middleName = objectValue.split(" ")[1];
                String lastName = objectValue.split(" ")[2];
                Name name = Name.builder().firstName(firstName)
                        .middleName(middleName)
                        .lastName(lastName).build();
                field.set(object, name);
            } else {
                field.set(object, objectValue);
            }

            field.setAccessible(false);
        }
    }
}
