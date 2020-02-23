package local.test.mongodb;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentTest {

    private static MongoTemplate mongoTemplate;

    static {
        mongoTemplate = (MongoTemplate) new ClassPathXmlApplicationContext("classpath:spring.xml").getBean("mongoTemplate");
    }

    public static void main(String[] args) {
        insert();

    }

    public static void insert() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("张三"+i);
            student.setAge(16+i);
            student.setAddTime(new Date());
            list.add(student);
        }
        mongoTemplate.insert(list, Student.class);
    }
}
