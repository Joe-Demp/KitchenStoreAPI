package ie.dempsey.kitchenstore.infrastructure.repositories;

import ie.dempsey.kitchenstore.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
