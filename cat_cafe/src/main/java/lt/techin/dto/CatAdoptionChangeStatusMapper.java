package lt.techin.dto;

import lt.techin.model.CatAdoption;

public class CatAdoptionChangeStatusMapper {


    public static void updateCatAdoptionFromDTO(CatAdoption catAdoption, CatAdoptionChangeStatusDTO catAdoptionChangeStatusDTO) {
        catAdoption.setCatAdoptionStatus(catAdoptionChangeStatusDTO.catAdoptionStatus());
    }
}
