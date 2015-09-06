package ua.com.studhero.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Eugene on 06.09.2015.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassId {

    long id() default 0;
}
