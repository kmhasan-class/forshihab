package bd.edu.seu.springbackenddemo;

import bd.edu.seu.springbackenddemo.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBackendDemoApplicationTests2 {
    private RestTemplate restTemplate;
    private static final String resourceUrl = "http://localhost:8080/student";
    private Student std;

    @Before
    public void initializing(){
        restTemplate = new RestTemplate();
    }

    @Test
    public void givenResourceUrl_whenSendGetForRequestEntity_thenStatusOk(){
        final ResponseEntity<Student> response = restTemplate.getForEntity(resourceUrl + "/1234", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenStudentService_whenGetForObject_thenObjectIsReturned(){
        final Student student = restTemplate.getForObject(resourceUrl + "/1234", Student.class);
        assertThat(student.getName()).isEqualTo("Shihab");
        assertThat(student.getId()).isEqualTo(1234);
        assertThat(student.getCgpa()).isBetween(2.00, 4.00);

    }

    @Test(expected = HttpServerErrorException.class)
    public void givenStudentService_whenGetForEntity_httpServerErrorExceptionIsReturned(){
        final ResponseEntity<Student> response = restTemplate.getForEntity(resourceUrl + "/1238", Student.class);
    }

    @Test
    public void givenUrl_thenStudentListIsReturned(){
        final Student[] studentList = restTemplate.getForObject(resourceUrl + "/all", Student[].class);
        assertThat(studentList).contains(new Student(1234, "Shihab", 3.12));
        assertThat(studentList).hasSize(5);
    }

}
