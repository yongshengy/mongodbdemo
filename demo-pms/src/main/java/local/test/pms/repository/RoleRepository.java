package local.test.pms.repository;

import local.test.pms.entity.Role;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

    public PageImpl findAll(Pageable pageable);
}
