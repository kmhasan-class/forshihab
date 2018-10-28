package bd.edu.seu.springbackenddemo.repository;

import bd.edu.seu.springbackenddemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
