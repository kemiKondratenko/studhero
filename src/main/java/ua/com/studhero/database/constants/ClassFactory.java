package ua.com.studhero.database.constants;

import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.StudentUser;

/**
 * Created by Eugene on 11.09.2015.
 */
public class ClassFactory {
    public static Class getClassById(long primaryClassId) {
        if(StudentUser.class.getAnnotation(ClassId.class).id() == primaryClassId)
            return StudentUser.class;
        if(Company.class.getAnnotation(ClassId.class).id() == primaryClassId)
            return Company.class;
        return null;
    }
}
