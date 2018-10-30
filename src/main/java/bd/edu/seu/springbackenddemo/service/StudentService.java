package bd.edu.seu.springbackenddemo.service;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


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
