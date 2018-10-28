package bd.edu.seu.springbackenddemo.controller;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/student")
@Component
public class StudentController{
    private StudentService studentService;
    //private Map<Integer, Student> studentMap;

    @GetMapping(value = "/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/rall")
    public ResponseEntity<List<Student>> getAllStudents1() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Student student = studentService.getStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudent(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Student> createStudent(@RequestBody final Student student){
        studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);

        /*final Student found = studentService.getStudent(student.getId());
        if(found == null){
            studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }*/
    }

}
