package bd.edu.seu.springbackenddemo.controller;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(value = "/student/all")
    // need to modify this so that we return response entity
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/student/rall")
    public ResponseEntity<List<Student>> getAllStudents1() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @PostMapping(value = "/forpost")
    public ResponseEntity<Student> forPost(@RequestParam int id){
        HttpHeaders hd = new HttpHeaders();
        return ResponseEntity.ok().header("Custom Header", "application/json").body(studentService.getStudent(id));
    }

    // write code to test this service
}
