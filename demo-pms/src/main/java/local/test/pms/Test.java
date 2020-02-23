package local.test.pms;

import local.test.pms.entity.Authority;
import local.test.pms.repository.AuthorityRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    private static AuthorityRepository authorityRepository;
    static {
        authorityRepository = (AuthorityRepository) new ClassPathXmlApplicationContext("classpath:spring.xml").getBean("authorityRepository");
    }

    public static void main(String[] args) {
        Iterable<Authority> authorities = authorityRepository.findAll();
        System.out.println(authorities);
    }
}
