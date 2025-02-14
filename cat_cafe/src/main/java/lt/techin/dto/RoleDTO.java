package lt.techin.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(long id,
                      @NotBlank
                      String name) {


}
