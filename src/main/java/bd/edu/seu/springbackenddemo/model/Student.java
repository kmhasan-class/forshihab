package bd.edu.seu.springbackenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private double cgpa;
}
