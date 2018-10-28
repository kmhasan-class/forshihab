package bd.edu.seu.springbackenddemo.controller;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;
    private final Map<Integer, Student> studentMap = new HashMap<Integer, Student>();

    private void mapInitializing(){
        List<Student> demoList = studentService.getAllStudents();
        for(Student student: demoList)
            studentMap.put(student.getId(), student);
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
        mapInitializing();
        Student student = studentMap.get(id);
        if(student == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(student);
        else return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudent(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Student> createStudent(@RequestBody final Student student){
        mapInitializing();
        final Student found = studentMap.get(student.getId());
        if(found == null){
            studentService.saveStudent(student);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId()).toUri();
            System.out.println(location.toString());
            return ResponseEntity.created(location).build();
        }
        else{
            throw new IllegalArgumentException("Bad Request!");
        }
    }

    // write code to test this service
}
