package local.test.mongodb;

import local.test.mongodb.entity.Student;
import local.test.mongodb.repository.StudentRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class Test {

    public static void main(String[] args) {
        StudentRepository studentRepository = (StudentRepository) new ClassPathXmlApplicationContext("classpath:spring.xml").getBean("studentRepository");
        System.out.println(studentRepository.count());
        Optional<Student> student = studentRepository.findById("5e430de857e56956a447a9d1");
        System.out.println(student.get().getName());
        System.out.println(studentRepository.findAll(Sort.by(Sort.Direction.DESC, "age")).get(0).getAge());
    }
}
