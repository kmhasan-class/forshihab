package bd.edu.seu.springbackenddemo;

import bd.edu.seu.springbackenddemo.model.Student;
import org.assertj.core.internal.bytebuddy.description.type.TypeDescription;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.*;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldHaveNoViolations(){
        Student student = new Student(1239, "Ripon", 3.80);
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidName(){
        Student student1 = new Student(1239, "a", 3.80);
        Set<ConstraintViolation<Student>> violations = validator.validate(student1);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Student> violation = violations.iterator().next();
        assertEquals(violation.getMessage(), "name must be within 5 or 15 characters");

        Student student2 = new Student(1240, null, 3.80);
        violations = validator.validate(student2);
        violation = violations.iterator().next();
        assertEquals(violations.size(), 1);
        assertEquals("name must not be null", violation.getMessage());

        Student student3 = new Student(1240, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 3.80);
        violations = validator.validate(student3);
        violation = violations.iterator().next();
        assertEquals(violations.size(), 1);
        assertEquals("name must be within 5 or 15 characters", violation.getMessage());
    }

    @Test
    public void shouldDetectInvalidId(){
        Student student1 = new Student(123, "abacde", 3.80);
        Set<ConstraintViolation<Student>> violations = validator.validate(student1);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Student> violation = violations.iterator().next();
        assertEquals(violation.getMessage(), "id should not be less than 1000");

        Student student2 = new Student(12345, "abacde", 3.80);
        violations = validator.validate(student2);
        violation = violations.iterator().next();
        assertEquals(violations.size(), 1);
        assertEquals("id should not be greater than 9999", violation.getMessage());
    }

    @Test
    public void shouldDetectInvalidCgpa(){
        Student student1 = new Student(1234, "abacde", 1.99);
        Set<ConstraintViolation<Student>> violations = validator.validate(student1);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Student> violation = violations.iterator().next();
        assertEquals(violation.getMessage(), "cgpa should not be less than 2.00");

        Student student2 = new Student(1234, "abacde", 4.01);
        violations = validator.validate(student2);
        violation = violations.iterator().next();
        assertEquals(violations.size(), 1);
        assertEquals("cgpa should not be greater than 4.00", violation.getMessage());
    }

    @AfterClass
    public static void closeValidator(){
        validatorFactory.close();
    }

}
