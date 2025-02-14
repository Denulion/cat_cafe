package lt.techin.dto;

import jakarta.validation.constraints.NotBlank;
import lt.techin.validation.Capitalized;

public record CatAdoptionRequestDTO(@NotBlank
                                    @Capitalized
                                    String catName) {
}
