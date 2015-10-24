package ua.com.studhero.controllers.entity;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.controllers.entity.model.StudentRegistrateModel;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.database.entities.BaseDBO;
import ua.com.studhero.database.entities.valueholders.BooleanResult;
import ua.com.studhero.mail.Emailer;
import ua.com.studhero.model.entity.Event;
import ua.com.studhero.model.entity.Student;
import ua.com.studhero.model.entity.User;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Eugene on 03.10.2015.
 */
@Controller
@RequestMapping("students")
public class StudentController {

    @Inject
    private DataBaseWorker dataBaseWorker;
    Logger log = Logger.getLogger("Student rest");


    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody
    List<Student> get(){
        try {
            return dataBaseWorker.get(Student.class);
        }  catch (Exception e) {
            return Lists.newArrayList(new Student(e.getMessage()));
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Student getById(@PathVariable long id){
        try {
            return dataBaseWorker.getFull(id, Student.class);
        }  catch (Exception e) {
            return new Student(e.getMessage());
        }
    }

    @RequestMapping(value="/update", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Student update(@RequestBody Student entity){
        try {
            if(entity.getObjectId() == 0){
                return new Student("Object does not have an id");
            }
            dataBaseWorker.update(entity);
            return dataBaseWorker.get(entity.getObjectId(), entity.getClass());
        } catch (Exception e) {
            return new Student(e.getMessage());
        }
    }

    @RequestMapping(value="/registrate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Student registrate(@RequestBody StudentRegistrateModel entity){
        log.info("resistrate:"+entity.getUser().getEmail());
        log.info("city:"+entity.getStudent().getCity());
        try {
            long newId = dataBaseWorker.createLoginable(entity.getUser().getEmail(), entity.getUser().getPassword());
            if(newId != 0){
                dataBaseWorker.save(newId, entity.getStudent());
                Emailer.sendRegistrationEmailToStudent(
                        entity.getUser().getEmail(),
                        entity.getStudent().getName() + entity.getStudent().getLastName(),
                        entity.getUser().getPassword());
                return dataBaseWorker.get(newId, Student.class);
            }else
                return new Student("cant create student");
        } catch (Exception e) {
            return new Student(e.getMessage());
        }
    }

    @RequestMapping(value="/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Student login(@RequestBody User entity){

        log.info("Login student");

        try {
            long id =  dataBaseWorker.getIdIfExists(entity);
            log.info("id: "+id);
            long classId_id =  dataBaseWorker.getPrimaryClassId(id);
            log.info("classId: "+classId_id);
            long company_classId_id =  Student.class.getAnnotation(ClassId.class).id();
            if(id == 0 || classId_id != company_classId_id) return new Student("No such student");
            return dataBaseWorker.get(id, Student.class);
        } catch (Exception e) {
            return new Student(e.getMessage());
        }
    }

    @RequestMapping(value="/{idStudent}/subscribe/{idEvent}", method = RequestMethod.GET)
    public @ResponseBody
    BaseDBO subscribe(@PathVariable long idStudent, @PathVariable long idEvent){
        try {
            Student student = dataBaseWorker.getFull(idStudent, Student.class);
            if (student.getEvents() != null) {
                student.getEvents().add(new Event(idEvent));
            } else {
                student.setEvents(Lists.newArrayList(new BaseDBO(idEvent)));
            }
            BaseDBO obj = new BaseDBO();
            if (dataBaseWorker.save(idStudent, student) != 0) {
                obj.setObjectId(idStudent);
            } else {
                obj.setError("Problems with registrating student for event");
            }
            return obj;
        } catch (Exception e) {
            return new BaseDBO(e.getMessage());
        }
    }

    @RequestMapping(value = "/{idStudent}/isSubscribed/{idEvent}", method = RequestMethod.GET)
    public
    @ResponseBody
    BooleanResult isSubscribed(@PathVariable long idStudent, @PathVariable long idEvent) {
        try {
            Student student = dataBaseWorker.getFull(idStudent, Student.class);
            if (student.getEvents() != null) {
                for (BaseDBO obj : student.getEvents()) {
                    if (obj.getObjectId() == idEvent) {
                        return new BooleanResult(true);
                    }
                }
            }
        } catch (Exception e) {
            return new BooleanResult(e.getMessage());
        }
        return new BooleanResult(false);
    }

    @RequestMapping(value="/{idStudent}/unsubscribe/{idEvent}", method = RequestMethod.GET)
    public @ResponseBody
    BaseDBO unsubscribe(@PathVariable long idStudent, @PathVariable final long idEvent){
        try {
            Student student = dataBaseWorker.getFull(idStudent, Student.class);
            if (student.getEvents() != null) {
                student.getEvents().remove(Iterables.indexOf(student.getEvents(), new Predicate<BaseDBO>() {
                    @Override
                    public boolean apply(BaseDBO baseDBO) {
                        return baseDBO.getObjectId() == idEvent;
                    }
                }));
            } else {
                student.setEvents(Lists.newArrayList(new BaseDBO(idEvent)));
            }
            BaseDBO obj = new BaseDBO();
            if (dataBaseWorker.save(idStudent, student) != 0) {
                obj.setObjectId(idStudent);
            } else {
                obj.setError("Problems with registrating student for event");
            }
            return obj;
        } catch (Exception e) {
            return new BaseDBO(e.getMessage());
        }
    }
}
