package bd.edu.seu.springbackenddemo;

<<<<<<< HEAD
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

=======
import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import bd.edu.seu.springbackenddemo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


>>>>>>> Adding test cases
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBackendDemoApplicationTests {

<<<<<<< HEAD
    @Test
    public void contextLoads() {
    }

=======
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;


    private Student std;
    private List<Student> studentList = new ArrayList<>();

    @Before
    public void initializing(){
        std = new Student(1234, "Shihab", 3.12);
        studentRepository.save(std);
        studentList = studentService.getAllStudents();

    }

    @Test
    public void testStudent(){
        String name = "shihab";
        assertThat(std.getName()).as("After create a new student expect:%s Found:%s",name, std.getName()).isEqualTo(name);
        assertThat(std.getId()).isEqualTo(1234).isBetween(1000, 10000);
        assertThat(std.getCgpa()).isEqualTo(3.12).isBetween(2.00, 4.00);
    }


    //@Ignore("studentList.size() might be smaller or greater than 3")
    @Test
    public void testStudentList(){
        Student ts = null;
        assertThat(studentList).hasSize(4).contains(std).doesNotContain(ts);
    }

    @Test
    public void findStudentById(){
        Student found = studentService.getStudent(1234);
        assertThat(found).isEqualTo(std);
    }


>>>>>>> Adding test cases
}
