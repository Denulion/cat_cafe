package lt.techin.dto;

import lt.techin.model.CatAdoption;

import java.util.List;

public class CatAdoptionResponseMapper {


    public static CatAdoptionResponseDTO toCatAdoptionResponseDTO(CatAdoption catAdoption) {
        return new CatAdoptionResponseDTO(catAdoption.getId(), UserResponseMapper.toUserResponseDTO(catAdoption.getUser()),
                catAdoption.getCatName(), catAdoption.getCatAdoptionStatus(), catAdoption.getApplicationDate());
    }

    public static List<CatAdoptionResponseDTO> toCatAdoptionResponseDTOList(List<CatAdoption> catAdoptions) {
        return catAdoptions.stream()
                .map(catAdoption -> new CatAdoptionResponseDTO(catAdoption.getId(),
                        UserResponseMapper.toUserResponseDTO(catAdoption.getUser()),
                        catAdoption.getCatName(), catAdoption.getCatAdoptionStatus(),
                        catAdoption.getApplicationDate()))
                .toList();
    }
}
