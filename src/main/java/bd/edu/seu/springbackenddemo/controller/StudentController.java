package bd.edu.seu.springbackenddemo.controller;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {
    private StudentService studentService;
    private Map<Integer, Student> studentMap;

    public StudentController(){
        this.studentMap = new HashMap<Integer, Student>();
        List<Student> demoList = this.studentService.getAllStudents();
        for(Student student: demoList)
            this.studentMap.put(student.getId(), student);
    }

    // need to modify this so that we return response entity
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
        Student student = this.studentMap.get(id);
        if(student == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(student);
        else return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudent(id));
    }

       @PostMapping(value = "/new")
    public ResponseEntity<Student> createStudent(@RequestBody final Student student){
        final Student found = this.studentMap.get(student.getId());
        if(found == null){
            studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // write code to test this service
}
