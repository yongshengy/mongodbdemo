package local.test.pms.repository;

import local.test.pms.entity.Authority;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String> {
    public PageImpl findAll(Pageable pageable);
}
