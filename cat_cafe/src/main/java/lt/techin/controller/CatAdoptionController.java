package lt.techin.controller;

import jakarta.validation.Valid;
import lt.techin.dto.*;
import lt.techin.model.CatAdoption;
import lt.techin.service.CatAdoptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<CatAdoption>> getCatAdoptions(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(catAdoptionService.getCatAdoptionsByUsername(username));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/adoptions/apply")
    public ResponseEntity<?> postAdoption(@Valid @RequestBody CatAdoptionRequestDTO catAdoptionRequestDTO) {
        if (catAdoptionService.existsReservationByName(catAdoptionRequestDTO.catName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This cat is already requested for adoption!");
        }

        CatAdoption savedCatAdoption = catAdoptionService
                .save(CatAdoptionRequestMapper.toCatAdoption(catAdoptionRequestDTO));

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedCatAdoption.getId())
                                .toUri())
                .body(CatAdoptionResponseMapper.toCatAdoptionResponseDTO(savedCatAdoption));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/pending")
    public ResponseEntity<List<CatAdoptionResponseDTO>> getPendingAdoptions() {
        return ResponseEntity.ok(CatAdoptionResponseMapper.toCatAdoptionResponseDTOList(catAdoptionService.getPendingAdoptions()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{adoptionId}/approve")
    public ResponseEntity<?> approveAdoption(@PathVariable Long adoptionId, @Valid @RequestBody CatAdoptionApprovalDTO catAdoptionApprovalDTO) {
        if (!catAdoptionService.existsCatAdoptionById(adoptionId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoption request not found.");
        }
        CatAdoption catAdoptionFromDB = catAdoptionService.findById(adoptionId).get();

        CatAdoptionApprovalMapper.updateCatAdoptionFromDTO(catAdoptionFromDB, catAdoptionApprovalDTO);

        catAdoptionService.save(catAdoptionFromDB);

        return ResponseEntity.ok(CatAdoptionApprovalResponseMapper.toCatAdoptionApprovalResponseDTO(catAdoptionFromDB));
    }
}
