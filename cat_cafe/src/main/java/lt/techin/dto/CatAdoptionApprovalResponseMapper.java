package lt.techin.dto;

import lt.techin.model.CatAdoption;

public class CatAdoptionApprovalResponseMapper {


    public static CatAdoptionApprovalResponseDTO toCatAdoptionApprovalResponseDTO(CatAdoption catAdoption) {
        return new CatAdoptionApprovalResponseDTO(catAdoption.getId(), catAdoption.getCatName(), catAdoption.getCatAdoptionStatus());
    }
}
