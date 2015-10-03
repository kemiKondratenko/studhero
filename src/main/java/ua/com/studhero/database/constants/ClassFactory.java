package ua.com.studhero.database.constants;

import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.model.entity.Student;
import ua.com.studhero.model.entity.Tag;

/**
 * Created by Eugene on 11.09.2015.
 */
public class ClassFactory {
    public static Class getClassById(long primaryClassId) {
        if(Student.class.getAnnotation(ClassId.class).id() == primaryClassId)
            return Student.class;
        if(Company.class.getAnnotation(ClassId.class).id() == primaryClassId)
            return Company.class;
        if(Tag.class.getAnnotation(ClassId.class).id() == primaryClassId)
            return Tag.class;
        if(Event.class.getAnnotation(ClassId.class).id() == primaryClassId)
            return Event.class;
        return null;
    }
}
