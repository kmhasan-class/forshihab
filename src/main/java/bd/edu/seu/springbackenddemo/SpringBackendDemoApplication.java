package bd.edu.seu.springbackenddemo;

import bd.edu.seu.springbackenddemo.service.DummyDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBackendDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBackendDemoApplication.class, args);
        //run.getBean(DummyDataService.class).persistDummyData();
    }
}
