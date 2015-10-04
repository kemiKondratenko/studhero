package ua.com.studhero.controllers.entity;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.studhero.annotations.ClassId;
import ua.com.studhero.controllers.entity.model.CompanyRegistrateModel;
import ua.com.studhero.controllers.entity.model.StudentRegistrateModel;
import ua.com.studhero.database.DataBaseWorker;
import ua.com.studhero.model.entity.Company;
import ua.com.studhero.model.entity.Student;
import ua.com.studhero.model.entity.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eugene on 03.10.2015.
 */
@Controller
@RequestMapping("students")
public class StudentController {

    @Inject
    private DataBaseWorker dataBaseWorker;


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
            return dataBaseWorker.get(id, Student.class);
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
            return dataBaseWorker.get(dataBaseWorker.save(entity.getObjectId(), entity), entity.getClass());
        } catch (Exception e) {
            return new Student(e.getMessage());
        }
    }

    @RequestMapping(value="/registrate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Student registrate(@RequestBody StudentRegistrateModel entity){
        try {
            long newId = dataBaseWorker.createLoginable(entity.getUser().getEmail(), entity.getUser().getPassword());
            if(newId != 0){
                dataBaseWorker.save(newId, entity.getStudent());
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
        try {
            long id =  dataBaseWorker.getIdIfExists(entity);
            long classId_id =  dataBaseWorker.getPrimaryClassId(id);
            long company_classId_id =  Student.class.getAnnotation(ClassId.class).id();
            if(id == 0 || classId_id != company_classId_id) return new Student("No such student");
            return dataBaseWorker.get(id, Student.class);
        } catch (Exception e) {
            return new Student(e.getMessage());
        }
    }

}
