package com.github.skystardust.InputMethodBlocker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static Object getPrivateField(Class clazz,String name,Object object) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        Object returnObject = field.get(object);
        return returnObject;
    }
    public static List<Field> getPrivateObjectList(Class clazz, Class type, Object object) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<Field>();
        for (int i=0;i<fields.length;i++){
            if (fields[i].getType().equals(type)){
                list.add(fields[i]);
            }
        }
        return list;
    }
}
