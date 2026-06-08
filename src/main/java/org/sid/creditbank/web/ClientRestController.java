package org.sid.creditbank.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.sid.creditbank.dtos.ClientDTO;
import org.sid.creditbank.dtos.CreditDTO;
import org.sid.creditbank.exceptions.ClientNotFoundException;
import org.sid.creditbank.services.CreditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientRestController {

    private CreditService creditService;

    @GetMapping
    @Operation(summary = "Liste de tous les clients")
    public List<ClientDTO> listClients() {
        return creditService.listClients();
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher un client par nom")
    public List<ClientDTO> searchClients(@RequestParam(defaultValue = "") String keyword) {
        return creditService.searchClients(keyword);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un client par son id")
    public ClientDTO getClient(@PathVariable Long id) throws ClientNotFoundException {
        return creditService.getClient(id);
    }

    @PostMapping
    @Operation(summary = "Ajouter un nouveau client")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return creditService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return creditService.updateClient(clientDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    public void deleteClient(@PathVariable Long id) {
        creditService.deleteClient(id);
    }

    @GetMapping("/{id}/credits")
    @Operation(summary = "Crédits d'un client")
    public List<CreditDTO> getCreditsByClient(@PathVariable Long id) {
        return creditService.getCreditsByClient(id);
    }
}
