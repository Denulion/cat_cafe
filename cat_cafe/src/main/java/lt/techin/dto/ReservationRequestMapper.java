package lt.techin.dto;

import lt.techin.model.Reservation;

public class ReservationRequestMapper {


    public static Reservation toReservation(ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = new Reservation();

        reservation.setDateOfReservation(reservationRequestDTO.dateOfReservation());
        reservation.setTimeSlot(reservationRequestDTO.timeSlot());
        reservation.setNumGuests(reservationRequestDTO.numGuests());

        return reservation;
    }
}
