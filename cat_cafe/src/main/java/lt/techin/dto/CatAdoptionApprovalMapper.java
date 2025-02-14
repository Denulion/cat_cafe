package lt.techin.dto;

import lt.techin.model.CatAdoption;

public class CatAdoptionApprovalMapper {


    public static void updateCatAdoptionFromDTO(CatAdoption catAdoption, CatAdoptionApprovalDTO catAdoptionApprovalDTO) {
        catAdoption.setCatAdoptionStatus(catAdoptionApprovalDTO.catAdoptionStatus());
    }
}
