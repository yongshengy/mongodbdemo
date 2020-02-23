package local.test.pms.repository;

import local.test.pms.entity.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    public User findByName(String name);
    public PageImpl findAll(Pageable pageable);
}
