package bd.edu.seu.springbackenddemo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import bd.edu.seu.springbackenddemo.model.Student;
import bd.edu.seu.springbackenddemo.repository.StudentRepository;
import bd.edu.seu.springbackenddemo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBackendDemoApplicationTests {

    private RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080/student/rall";
    private Student std;
    private List<Student> studentList = new ArrayList<>();
    private Student[] studentList2;

    @Before
    public void initializing() throws URISyntaxException {
        URI uri = new URI(baseUrl);
        ResponseEntity<List<Student>> result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
        studentList = result.getBody();
        studentList2 = restTemplate.getForObject(uri, Student[].class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testForNull() {
        assertThat(studentList2).hasSize(4);
        for (Student s : studentList2) {
            assertThat(s.getName()).isNotNull();
            assertThat(s.getId()).isNotNull();
            assertThat(s.getCgpa()).isNotNull();
        }

    }

    @Test
     public void testForRange(){
        for(Student s : studentList2){
            assertThat(s.getId()).isBetween(1000, 10000);
            assertThat(s.getCgpa()).isBetween(2.00, 4.00);
        }
    }


    @Test
    public void findStudent(){
        assertThat(studentList).contains(new Student(1234, "Shihab", 3.12));
    }
}
