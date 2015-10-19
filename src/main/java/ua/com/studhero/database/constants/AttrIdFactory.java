package ua.com.studhero.database.constants;

import ua.com.studhero.annotations.AttrId;
import ua.com.studhero.database.entities.valueholders.TextParam;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.model.entity.Student;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Created by Eugene on 19.09.2015.
 */
public class AttrIdFactory {

    public static long getAttrId(String paramName) throws NoSuchFieldException {
        Field field ;
        try {
            field = Event.class.getDeclaredField(paramName);
            if(field != null) return field.getAnnotation(AttrId.class).id();
        }catch (NoSuchFieldException e){
        }
        try {
            field = Company.class.getDeclaredField(paramName);
            if(field != null) return field.getAnnotation(AttrId.class).id();
        }catch (NoSuchFieldException e){
        }
        try {
            field = Student.class.getDeclaredField(paramName);
            if(field != null) return field.getAnnotation(AttrId.class).id();
        }catch (NoSuchFieldException e){
        }
        return 0;
    }

    public static long getAttrId(String paramName, Class type, boolean equals) throws NoSuchFieldException {
        Field field ;
        String typeName = type.getName();
        try {
            field = Event.class.getDeclaredField(paramName);
            if(field != null && needToEqual(typeName, field.getType().getName(), equals)){
                return field.getAnnotation(AttrId.class).id();
            }
        }catch (NoSuchFieldException e){
        }
        try {
            field = Company.class.getDeclaredField(paramName);
            if(field != null && needToEqual(typeName, field.getType().getName(), equals)) {
                return field.getAnnotation(AttrId.class).id();
            }
        }catch (NoSuchFieldException e){
        }
        try {
            field = Student.class.getDeclaredField(paramName);
            if(field != null && needToEqual(typeName, field.getType().getName(), equals)) {
                return field.getAnnotation(AttrId.class).id();
            }
        }catch (NoSuchFieldException e){
        }
        return 0;
    }

    private static boolean needToEqual(String typeName, String name, boolean equals) {
        boolean eq = name.equals(typeName);
        return ((eq && equals)||(!eq && !equals));
    }

}
