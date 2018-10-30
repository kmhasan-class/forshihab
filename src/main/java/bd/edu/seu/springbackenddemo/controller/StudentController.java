package bd.edu.seu.springbackenddemo.controller;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import bd.edu.seu.springbackenddemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController{
    private StudentService studentService;
    private Map<Integer, Student> studentMap;
    private List<Student> studentList;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
        this.studentMap = new HashMap<Integer, Student>();
        this.studentList = studentService.getAllStudents();
        for(Student std : this.studentList)
            studentMap.put(std.getId(), std);
    }

    @GetMapping(value = "/all")
    public List<Student> getAllStudents() {
        return studentList;
    }

    @GetMapping(value = "/rall")
    public ResponseEntity<List<Student>> getAllStudents1() {
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Student student = studentService.getStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentMap.get(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Student> createStudent(@RequestBody final Student student){
        studentService.saveStudent(student);
        studentMap.put(student.getId(), student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

}
