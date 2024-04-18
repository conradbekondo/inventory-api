package ca.quickdo.springintro.repository;

import ca.quickdo.springintro.models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {
    boolean existsByName(String name);
}
