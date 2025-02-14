package lt.techin.dto;

import jakarta.validation.constraints.NotBlank;
import lt.techin.model.CatAdoptionStatus;
import lt.techin.validation.Capitalized;

public record CatAdoptionApprovalResponseDTO(long id,
                                             @NotBlank
                                             @Capitalized
                                             String catName,
                                             CatAdoptionStatus catAdoptionStatus) {


}
