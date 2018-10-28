package bd.edu.seu.springbackenddemo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import bd.edu.seu.springbackenddemo.model.Student;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBackendDemoApplicationTests1 {
    private RestTemplate restTemplate;
    private static final String resourceUrl = "http://localhost:8080/student";

    @Before
    public void initializing(){
        restTemplate = new RestTemplate();
    }

    @Test
    public void givenResourceUrl_whenPostForObject_thenCreatedObjedtIsReturned(){
        final HttpEntity<Student> request = new HttpEntity<>(new Student(1236, "Rian", 3.84));
        final ResponseEntity<Student> response = restTemplate.postForEntity(resourceUrl + "/new", request, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        final Student std = response.getBody();
        assertThat(std.getId()).isEqualTo(1236);
        assertThat(std.getCgpa()).isBetween(2.00, 4.00);
        assertThat(std.getName()).isNotNull();
    }

    @Test(expected = HttpServerErrorException.class)
    public void givenResourceUrl_whenPostForObject_thenErrorIsReturned(){
        final HttpEntity<Student> request = new HttpEntity<>(new Student());
        final ResponseEntity<Student> response = restTemplate.postForEntity(resourceUrl + "/new", request, Student.class);
    }



}
