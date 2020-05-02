package ie.dempsey.kitchenstore.infrastructure.repositories;

import ie.dempsey.kitchenstore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
