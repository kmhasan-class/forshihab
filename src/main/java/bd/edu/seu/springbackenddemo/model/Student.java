package bd.edu.seu.springbackenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @NotNull(message = "id must not be null")
    @Min(value = 1000, message = "id should not be less than 1000")
    @Max(value = 9999, message = "id should not be greater than 9999")
    private int id;

    @NotNull(message = "name must not be null")
    @Size(min = 5, max = 15, message = "name must be within 5 or 15 characters")
    private String name;

    @NotNull
    @DecimalMin(value = "2.00", message = "cgpa should not be less than 2.00")
    @DecimalMax(value = "4.00", message = "cgpa should not be greater than 4.00")
    private double cgpa;
}
