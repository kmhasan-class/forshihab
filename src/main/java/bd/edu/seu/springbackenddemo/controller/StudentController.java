package bd.edu.seu.springbackenddemo.controller;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.service.StudentService;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> Adding test cases
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

<<<<<<< HEAD
=======
    @GetMapping(value = "/student/rall")
    public ResponseEntity<List<Student>> getAllStudents1() {
        return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
    }

>>>>>>> Adding test cases
    // write code to test this service
}
