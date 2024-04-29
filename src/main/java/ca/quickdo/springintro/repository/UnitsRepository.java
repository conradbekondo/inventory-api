package ca.quickdo.springintro.repository;

import ca.quickdo.springintro.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepository extends JpaRepository<Unit, Integer> {
    // Additional custom query methods can be defined here if needed

    Unit save(Unit unit);
}