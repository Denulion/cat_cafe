package lt.techin.dto;

import jakarta.validation.constraints.NotBlank;
import lt.techin.model.CatAdoptionStatus;
import lt.techin.validation.Capitalized;

import java.time.LocalDate;

public record CatAdoptionResponseDTO(long id,
                                     UserResponseDTO userResponseDTO,
                                     @NotBlank
                                     @Capitalized
                                     String catName,
                                     CatAdoptionStatus catAdoptionStatus,
                                     LocalDate applicationDate) {
}
