package local.test.mongodb.repository;

import local.test.mongodb.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    public List<Student> findAll(Sort sort);
}
