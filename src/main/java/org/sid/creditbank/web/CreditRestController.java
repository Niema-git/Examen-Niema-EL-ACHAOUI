package org.sid.creditbank.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.sid.creditbank.dtos.CreditDTO;
import org.sid.creditbank.dtos.CreditImmobilierDTO;
import org.sid.creditbank.dtos.CreditPersonnelDTO;
import org.sid.creditbank.dtos.CreditProfessionnelDTO;
import org.sid.creditbank.enums.StatutCredit;
import org.sid.creditbank.exceptions.ClientNotFoundException;
import org.sid.creditbank.exceptions.CreditNotFoundException;
import org.sid.creditbank.services.CreditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Crédits", description = "Gestion des crédits")
public class CreditRestController {

    private CreditService creditService;

    @GetMapping
    @Operation(summary = "Liste de tous les crédits")
    public List<CreditDTO> listCredits() {
        return creditService.listCredits();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un crédit par son id")
    public CreditDTO getCredit(@PathVariable Long id) throws CreditNotFoundException {
        return creditService.getCredit(id);
    }

    @PostMapping("/personnel")
    @Operation(summary = "Ajouter un crédit personnel")
    public CreditDTO saveCreditPersonnel(@RequestBody CreditPersonnelDTO dto) throws ClientNotFoundException {
        return creditService.saveCreditPersonnel(dto);
    }

    @PostMapping("/immobilier")
    @Operation(summary = "Ajouter un crédit immobilier")
    public CreditDTO saveCreditImmobilier(@RequestBody CreditImmobilierDTO dto) throws ClientNotFoundException {
        return creditService.saveCreditImmobilier(dto);
    }

    @PostMapping("/professionnel")
    @Operation(summary = "Ajouter un crédit professionnel")
    public CreditDTO saveCreditProfessionnel(@RequestBody CreditProfessionnelDTO dto) throws ClientNotFoundException {
        return creditService.saveCreditProfessionnel(dto);
    }

    @PutMapping("/{id}/statut")
    @Operation(summary = "Mettre à jour le statut d'un crédit")
    public CreditDTO updateStatut(@PathVariable Long id,
                                  @RequestParam StatutCredit statut) throws CreditNotFoundException {
        return creditService.updateStatut(id, statut);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un crédit")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }
}