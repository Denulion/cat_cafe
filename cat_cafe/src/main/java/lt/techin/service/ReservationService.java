package lt.techin.service;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lt.techin.model.Reservation;
import lt.techin.model.User;
import lt.techin.repository.ReservationRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getReservationsByUsername(String username) {
        return reservationRepository.findByUser_Username(username);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }

    public void deleteReservationById(long id) {
        reservationRepository.deleteById(id);
    }

    public boolean existsReservationByTimeSlot(String value) {
        return reservationRepository.existsByTimeSlot(value);
    }

    public boolean existsReservationByDate(LocalDate date) {
        return reservationRepository.existsByDateOfReservation(date);
    }
}
