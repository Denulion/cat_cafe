package lt.techin.dto;

import jakarta.validation.Valid;
import lt.techin.model.CatAdoption;
import lt.techin.model.CatAdoptionStatus;

import java.time.LocalDate;

public class CatAdoptionRequestMapper {


    public static CatAdoption toCatAdoption(CatAdoptionRequestDTO catAdoptionRequestDTO) {
        CatAdoption catAdoption = new CatAdoption();

        catAdoption.setCatName(catAdoptionRequestDTO.catName());
        catAdoption.setCatAdoptionStatus(CatAdoptionStatus.PENDING);
        catAdoption.setApplicationDate(LocalDate.now());

        return catAdoption;
    }
}
