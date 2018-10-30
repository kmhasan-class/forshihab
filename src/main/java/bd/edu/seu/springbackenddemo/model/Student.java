package bd.edu.seu.springbackenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private double cgpa;
}
