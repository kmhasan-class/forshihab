package bd.edu.seu.springbackenddemo.service;

import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DummyDataService {
    private final StudentRepository studentRepository;

    public void persistDummyData() {
        Student s1 = new Student(1243, "John Doe", 2.33);
        Student s2 = new Student(4213, "Jane Doe", 3.42);
        Student s3 = new Student(7245, "Adam Smith", 3.59);

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }
}
