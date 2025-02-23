package lt.techin.dto;

import jakarta.validation.constraints.NotNull;
import lt.techin.model.CatAdoptionStatus;

public record CatAdoptionChangeStatusDTO(@NotNull(message = "Status cannot be null")
                                         CatAdoptionStatus status) {


}
