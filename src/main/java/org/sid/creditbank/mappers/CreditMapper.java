package org.sid.creditbank.mappers;
import org.sid.creditbank.dtos.*;
import org.sid.creditbank.entities.*;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    public ClientDTO toClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public Client toClient(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        return client;
    }

    public RemboursementDTO toRemboursementDTO(Remboursement r) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(r.getId());
        dto.setDate(r.getDate());
        dto.setMontant(r.getMontant());
        dto.setType(r.getType());
        dto.setCreditId(r.getCredit().getId());
        return dto;
    }

    public CreditDTO toCreditDTO(Credit credit) {
        if (credit instanceof CreditPersonnel cp) {
            CreditPersonnelDTO dto = new CreditPersonnelDTO();
            fillCreditDTO(dto, cp);
            dto.setMotif(cp.getMotif());
            dto.setType("CP");
            return dto;
        } else if (credit instanceof CreditImmobilier ci) {
            CreditImmobilierDTO dto = new CreditImmobilierDTO();
            fillCreditDTO(dto, ci);
            dto.setTypeBien(ci.getTypeBien());
            dto.setType("CI");
            return dto;
        } else if (credit instanceof CreditProfessionnel cpr) {
            CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
            fillCreditDTO(dto, cpr);
            dto.setMotif(cpr.getMotif());
            dto.setRaisonSociale(cpr.getRaisonSociale());
            dto.setType("CPR");
            return dto;
        }
        throw new RuntimeException("Type de crédit inconnu");
    }

    private void fillCreditDTO(CreditDTO dto, Credit credit) {
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcception(credit.getDateAcception());
        dto.setMontant(credit.getMontant());
        dto.setDuree(credit.getDuree());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient().getId());
        dto.setClientName(credit.getClient().getName());
    }
}