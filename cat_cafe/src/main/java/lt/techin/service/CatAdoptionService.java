package lt.techin.service;

import lt.techin.model.CatAdoption;
import lt.techin.model.CatAdoptionStatus;
import lt.techin.repository.CatAdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatAdoptionService {

    private final CatAdoptionRepository catAdoptionRepository;

    @Autowired
    public CatAdoptionService(CatAdoptionRepository catAdoptionRepository) {
        this.catAdoptionRepository = catAdoptionRepository;
    }

    public List<CatAdoption> getAllCatAdoptions() {
        return catAdoptionRepository.findAll();
    }

    public List<CatAdoption> getCatAdoptionsByUsername(String username) {
        return catAdoptionRepository.findByUser_Username(username);
    }

    public List<CatAdoption> getPendingAdoptions() {
        return catAdoptionRepository.findByCatAdoptionStatus(CatAdoptionStatus.PENDING);
    }

    public Optional<CatAdoption> findById(Long id) {
        return catAdoptionRepository.findById(id);
    }

    public boolean existsReservationByName(String name) {
        return catAdoptionRepository.existsByCatName(name);
    }

    public CatAdoption save(CatAdoption adoption) {
        return catAdoptionRepository.save(adoption);
    }

    public boolean existsCatAdoptionById(Long id) {
        return catAdoptionRepository.existsById(id);
    }

    public List<CatAdoption> findByStatus(CatAdoptionStatus status) {
        return catAdoptionRepository.findByCatAdoptionStatus(status);
    }
}
