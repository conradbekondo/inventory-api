package ca.quickdo.springintro.repository;

import ca.quickdo.springintro.models.Product;
import ca.quickdo.springintro.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findByIdGreaterThan(Long id, Pageable pageable);
    boolean existsByName(String name);
}