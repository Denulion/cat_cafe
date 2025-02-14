package lt.techin.repository;

import lt.techin.model.Reservation;
import lt.techin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    List<Reservation> findByUser_Username(String username);

    boolean existsByTimeSlot(String time);

    boolean existsByDateOfReservation(LocalDate dateOfReservation);
}
