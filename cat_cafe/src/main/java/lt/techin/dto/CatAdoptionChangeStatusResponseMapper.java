package lt.techin.dto;

import lt.techin.model.CatAdoption;

public class CatAdoptionChangeStatusResponseMapper {


    public static CatAdoptionChangeStatusResponseDTO toCatAdoptionChangeStatusResponseDTO(CatAdoption catAdoption) {
        return new CatAdoptionChangeStatusResponseDTO(catAdoption.getId(), catAdoption.getCatName(), catAdoption.getCatAdoptionStatus());
    }
}
