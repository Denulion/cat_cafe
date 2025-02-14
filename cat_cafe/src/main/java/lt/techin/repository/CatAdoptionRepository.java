package lt.techin.repository;

import lt.techin.model.CatAdoption;
import lt.techin.model.CatAdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatAdoptionRepository extends JpaRepository<CatAdoption, Long> {

    List<CatAdoption> findByUser_Username(String username);

    List<CatAdoption> findByCatAdoptionStatus(CatAdoptionStatus status);

    boolean existsByCatName(String catName);
}
