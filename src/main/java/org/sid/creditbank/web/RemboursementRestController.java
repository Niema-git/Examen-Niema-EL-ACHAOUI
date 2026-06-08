package org.sid.creditbank.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.sid.creditbank.dtos.RemboursementDTO;
import org.sid.creditbank.exceptions.CreditNotFoundException;
import org.sid.creditbank.services.CreditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Remboursements", description = "Gestion des remboursements")
public class RemboursementRestController {

    private CreditService creditService;

    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Remboursements d'un crédit")
    public List<RemboursementDTO> getRemboursements(@PathVariable Long creditId) {
        return creditService.getRemboursementsByCredit(creditId);
    }

    @PostMapping
    @Operation(summary = "Ajouter un remboursement")
    public RemboursementDTO saveRemboursement(@RequestBody RemboursementDTO dto) throws CreditNotFoundException {
        return creditService.saveRemboursement(dto);
    }
}
