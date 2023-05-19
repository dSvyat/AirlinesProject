package ua.vozniuk.AirlinesProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vozniuk.AirlinesProject.models.Plane;

@Repository
public interface PlanesRepository extends JpaRepository<Plane, Integer> {
}
