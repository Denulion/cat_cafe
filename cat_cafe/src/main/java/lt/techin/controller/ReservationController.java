package lt.techin.controller;

import jakarta.validation.Valid;
import lt.techin.dto.ReservationRequestDTO;
import lt.techin.dto.ReservationRequestMapper;
import lt.techin.dto.ReservationResponseDTO;
import lt.techin.dto.ReservationResponseMapper;
import lt.techin.model.Reservation;
import lt.techin.model.User;
import lt.techin.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getReservations(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        List<Reservation> reservations = isAdmin
                ? reservationService.getAllReservations()
                : reservationService.getReservationsByUsername(username);

        List<ReservationResponseDTO> responseDTO = reservations.stream()
                .map(ReservationResponseMapper::toReservationResponseDTO)
                .toList();

        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/reservations")
    public ResponseEntity<?> postReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO, Authentication authentication) {
        if (reservationService.existsReservationByTimeSlot(reservationRequestDTO.timeSlot()) &&
                reservationService.existsReservationByDate(reservationRequestDTO.dateOfReservation())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This time is already reserved!");
        }

        User authenticatedUser = (User) authentication.getPrincipal();
        Reservation reservation = ReservationRequestMapper.toReservation(reservationRequestDTO);
        reservation.setUser(authenticatedUser);

        Reservation savedReservation = reservationService.saveReservation(reservation);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedReservation.getId())
                                .toUri())
                .body(ReservationResponseMapper.toReservationResponseDTO(savedReservation));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable long reservationId) {
        if (!reservationService.existsById(reservationId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }

        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.status(HttpStatus.OK).body("Reservation successfully canceled");
    }
}
