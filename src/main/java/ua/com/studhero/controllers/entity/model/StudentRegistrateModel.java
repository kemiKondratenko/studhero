package ua.com.studhero.controllers.entity.model;


import ua.com.studhero.model.entity.Student;
import ua.com.studhero.model.entity.User;

/**
 * Created by Eugene on 03.10.2015.
 */
public class StudentRegistrateModel {

    private User user;
    private Student student;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
