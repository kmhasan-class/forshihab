package bd.edu.seu.springbackenddemo.service;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Component
public class StudentService{
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        studentIterable.forEach(studentList::add);
        return studentList;
    }

    public Student getStudent(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }


}
