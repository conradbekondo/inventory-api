package ca.quickdo.springintro.repository;

import ca.quickdo.springintro.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByIdGreaterThan(Integer id, Pageable pageable);
}