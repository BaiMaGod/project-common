package com.utils;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换工具
 */
public class ConvertUtil {


    /**
     * 将一个对象转换为另一个对象,并赋值，赋值规则，成员名和类型完全相同，才会赋值
     * @return
     */
    public static Object convert(Object srcObject,Object targetObject){
        if(srcObject==null || targetObject==null){
            return null;
        }
        Class<?> srcObjectClass = srcObject.getClass();
        Field[] srcObjectClassFields = srcObjectClass.getDeclaredFields();    // 原对象所有成员属性

        Class<?> targetObjectClass = targetObject.getClass();
        Field[] targetObjectClassFields = targetObjectClass.getDeclaredFields();    // 目标对象所有成员属性

        List<Field> equalsFields = getEqualsField(srcObjectClassFields, targetObjectClassFields);

        try {
            for (Field equalsField : equalsFields) {
                // 获取原值
                Field srcObjectClassField = srcObjectClass.getDeclaredField(equalsField.getName());
                srcObjectClassField.setAccessible(true);
                Object o = srcObjectClassField.get(srcObject);

                // 赋值
                equalsField.setAccessible(true);
                equalsField.set(targetObject,o);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            return targetObject;
        }

    }

    private static List<Field> getEqualsField(Field[] srcObjectClassFields, Field[] targetObjectClassFields) {
        List<Field> fields = new ArrayList<>();

        for (Field targetObjectClassField : targetObjectClassFields) {
            for (Field srcObjectClassField : srcObjectClassFields) {
                // 如果属性名相同，且类型相同
                if(srcObjectClassField.getName().equals(targetObjectClassField.getName()) && srcObjectClassField.getGenericType().toString().equals(targetObjectClassField.getGenericType().toString())){
                    fields.add(targetObjectClassField);
                    break;
                }
            }
        }

        return fields;
    }


}
