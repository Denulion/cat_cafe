package lt.techin.controller;

import jakarta.validation.Valid;
import lt.techin.dto.*;
import lt.techin.model.CatAdoption;
import lt.techin.model.CatAdoptionStatus;
import lt.techin.model.User;
import lt.techin.service.CatAdoptionService;
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
public class CatAdoptionController {

    private final CatAdoptionService catAdoptionService;

    public CatAdoptionController(CatAdoptionService catAdoptionService) {
        this.catAdoptionService = catAdoptionService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/adoptions")
    public ResponseEntity<List<CatAdoptionResponseDTO>> getCatAdoptions(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<CatAdoption> catAdoptions = catAdoptionService.getCatAdoptionsByUsername(username);
        List<CatAdoptionResponseDTO> responseDTOs = CatAdoptionResponseMapper.toCatAdoptionResponseDTOList(catAdoptions);
        return ResponseEntity.ok(responseDTOs);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/adoptions/apply")
    public ResponseEntity<?> postAdoption(@Valid @RequestBody CatAdoptionRequestDTO catAdoptionRequestDTO, Authentication authentication) {
        if (catAdoptionService.existsReservationByName(catAdoptionRequestDTO.catName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This cat is already requested for adoption!");
        }

        User authenticatedUser = (User) authentication.getPrincipal();
        CatAdoption adoption = CatAdoptionRequestMapper.toCatAdoption(catAdoptionRequestDTO);
        adoption.setUser(authenticatedUser);

        CatAdoption savedCatAdoption = catAdoptionService
                .save(adoption);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedCatAdoption.getId())
                                .toUri())
                .body(CatAdoptionResponseMapper.toCatAdoptionResponseDTO(savedCatAdoption));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/adoptions/pending")
    public ResponseEntity<List<CatAdoptionResponseDTO>> getPendingAdoptions() {
        return ResponseEntity.ok(CatAdoptionResponseMapper.toCatAdoptionResponseDTOList(catAdoptionService.getPendingAdoptions()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/adoptions/{adoptionId}/approve")
    public ResponseEntity<?> approveAdoption(@PathVariable Long adoptionId, @Valid @RequestBody CatAdoptionChangeStatusDTO catAdoptionChangeStatusDTO) {
        return getResponseEntity(adoptionId, catAdoptionChangeStatusDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/adoptions/{adoptionId}/reject")
    public ResponseEntity<?> rejectAdoption(@PathVariable Long adoptionId, @Valid @RequestBody CatAdoptionChangeStatusDTO catAdoptionChangeStatusDTO) {
        return getResponseEntity(adoptionId, catAdoptionChangeStatusDTO);
    }

    private ResponseEntity<?> getResponseEntity(@PathVariable Long adoptionId, @Valid @RequestBody CatAdoptionChangeStatusDTO catAdoptionChangeStatusDTO) {
        if (!catAdoptionService.existsCatAdoptionById(adoptionId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoption request not found.");
        }
        CatAdoption catAdoptionFromDB = catAdoptionService.findById(adoptionId).get();

        CatAdoptionChangeStatusMapper.updateCatAdoptionFromDTO(catAdoptionFromDB, catAdoptionChangeStatusDTO);

        catAdoptionService.save(catAdoptionFromDB);

        return ResponseEntity.ok(CatAdoptionChangeStatusResponseMapper.toCatAdoptionChangeStatusResponseDTO(catAdoptionFromDB));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/adoptions/approved")
    public ResponseEntity<List<CatAdoptionResponseDTO>> getApprovedAdoptions() {
        List<CatAdoption> approvedAdoptions = catAdoptionService.findByStatus(CatAdoptionStatus.APPROVED);
        return ResponseEntity.ok(approvedAdoptions.stream()
                .map(CatAdoptionResponseMapper::toCatAdoptionResponseDTO)
                .toList());
    }

}
