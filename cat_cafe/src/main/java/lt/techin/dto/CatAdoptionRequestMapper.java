package lt.techin.dto;

import jakarta.validation.Valid;
import lt.techin.model.CatAdoption;

public class CatAdoptionRequestMapper {


    public static CatAdoption toCatAdoption(CatAdoptionRequestDTO catAdoptionRequestDTO) {
        CatAdoption catAdoption = new CatAdoption();

        catAdoption.setCatName(catAdoptionRequestDTO.catName());

        return catAdoption;
    }
}
