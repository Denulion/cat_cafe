package lt.techin.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record ReservationResponseDTO(long id,
                                     UserResponseDTO user,
                                     @Future
                                     LocalDate dateOfReservation,
                                     @NotBlank
                                     @Length(max = 50)
                                     String timeSlot,
                                     @Min(1)
                                     @Max(4)
                                     int numGuests) {
}
