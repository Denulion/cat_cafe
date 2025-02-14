package lt.techin.dto;

import lt.techin.model.Reservation;

public class ReservationResponseMapper {

    public static Object toReservationResponseDTO(Reservation reservation) {
        return new ReservationResponseDTO(reservation.getId(),
                UserResponseMapper.toUserResponseDTO(reservation.getUser()), reservation.getDateOfReservation(),
                reservation.getTimeSlot(), reservation.getNumGuests());
    }
}
