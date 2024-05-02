package ca.quickdo.springintro.repository;

import ca.quickdo.springintro.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovementsRepository extends JpaRepository<Movement, Integer> {

    @Query("SELECT SUM(m.quantity) FROM Movement m JOIN m.unit u JOIN u.product p WHERE p.id = :productId")
    Optional<Integer> calculateTotalQuantityByProduct(@Param("productId") Integer productId);

}
