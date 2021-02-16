package bd.edu.seu.springbackenddemo;

import bd.edu.seu.springbackenddemo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBackendDemoApplicationTests2 {
    private RestTemplate restTemplate = new RestTemplate();
    private Student std;

    @Before
    public void initializing1() throws URISyntaxException, IOException {
        String baseUrl = "http://localhost:8080/forpost?id=1234";
        URI uri = new URI(baseUrl);
        //ResponseEntity<String> response = restTemplate.postForEntity(uri, HttpMethod.POST, String.class);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, null, String.class);
        std = new ObjectMapper().readValue(response.getBody(), Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testForPost(){
        assertThat(std).isEqualTo(new Student(1234, "Shihab", 3.12));
    }

    @After
    public void print(){
        System.out.println(std.getId() + " " + std.getName() + " " + std.getCgpa());
    }
}
