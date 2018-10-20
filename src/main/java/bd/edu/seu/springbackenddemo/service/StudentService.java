package bd.edu.seu.springbackenddemo.service;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        studentIterable.forEach(studentList::add);
        return studentList;
    }

    public Student getStudent(int studentId) {
        return studentRepository.findById(studentId).get();
    }
}
