package org.sid.creditbank.services;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.creditbank.dtos.*;
import org.sid.creditbank.entities.*;
import org.sid.creditbank.enums.StatutCredit;
import org.sid.creditbank.exceptions.ClientNotFoundException;
import org.sid.creditbank.exceptions.CreditNotFoundException;
import org.sid.creditbank.mappers.CreditMapper;
import org.sid.creditbank.repositories.ClientRepository;
import org.sid.creditbank.repositories.CreditRepository;
import org.sid.creditbank.repositories.RemboursementRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    private ClientRepository clientRepository;
    private CreditRepository creditRepository;
    private RemboursementRepository remboursementRepository;
    private CreditMapper mapper;


    @Override
    public ClientDTO saveClient(ClientDTO dto) {
        Client client = mapper.toClient(dto);
        return mapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(ClientDTO dto) {
        Client client = mapper.toClient(dto);
        return mapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO getClient(Long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable : " + id));
        return mapper.toClientDTO(client);
    }

    @Override
    public List<ClientDTO> listClients() {
        return clientRepository.findAll()
                .stream().map(mapper::toClientDTO).toList();
    }

    @Override
    public List<ClientDTO> searchClients(String keyword) {
        return clientRepository.findByNameContains(keyword)
                .stream().map(mapper::toClientDTO).toList();
    }

    // --- Crédits ---

    @Override
    public CreditDTO getCredit(Long id) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new CreditNotFoundException("Crédit introuvable : " + id));
        return mapper.toCreditDTO(credit);
    }

    @Override
    public List<CreditDTO> listCredits() {
        return creditRepository.findAll()
                .stream().map(mapper::toCreditDTO).toList();
    }

    @Override
    public List<CreditDTO> getCreditsByClient(Long clientId) {
        return creditRepository.findByClientId(clientId)
                .stream().map(mapper::toCreditDTO).toList();
    }

    @Override
    public CreditDTO saveCreditPersonnel(CreditPersonnelDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));
        CreditPersonnel cp = new CreditPersonnel();
        cp.setClient(client);
        cp.setDateDemande(new Date());
        cp.setStatut(StatutCredit.EN_COURS);
        cp.setMontant(dto.getMontant());
        cp.setDuree(dto.getDuree());
        cp.setTauxInteret(dto.getTauxInteret());
        cp.setMotif(dto.getMotif());
        return mapper.toCreditDTO(creditRepository.save(cp));
    }

    @Override
    public CreditDTO saveCreditImmobilier(CreditImmobilierDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));
        CreditImmobilier ci = new CreditImmobilier();
        ci.setClient(client);
        ci.setDateDemande(new Date());
        ci.setStatut(StatutCredit.EN_COURS);
        ci.setMontant(dto.getMontant());
        ci.setDuree(dto.getDuree());
        ci.setTauxInteret(dto.getTauxInteret());
        ci.setTypeBien(dto.getTypeBien());
        return mapper.toCreditDTO(creditRepository.save(ci));
    }

    @Override
    public CreditDTO saveCreditProfessionnel(CreditProfessionnelDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));
        CreditProfessionnel cpr = new CreditProfessionnel();
        cpr.setClient(client);
        cpr.setDateDemande(new Date());
        cpr.setStatut(StatutCredit.EN_COURS);
        cpr.setMontant(dto.getMontant());
        cpr.setDuree(dto.getDuree());
        cpr.setTauxInteret(dto.getTauxInteret());
        cpr.setMotif(dto.getMotif());
        cpr.setRaisonSociale(dto.getRaisonSociale());
        return mapper.toCreditDTO(creditRepository.save(cpr));
    }

    @Override
    public CreditDTO updateStatut(Long creditId, StatutCredit statut) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Crédit introuvable"));
        credit.setStatut(statut);
        if (statut == StatutCredit.ACCEPTE) {
            credit.setDateAcception(new Date());
        }
        return mapper.toCreditDTO(creditRepository.save(credit));
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    // --- Remboursements ---

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO dto) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(dto.getCreditId())
                .orElseThrow(() -> new CreditNotFoundException("Crédit introuvable"));
        Remboursement r = new Remboursement();
        r.setCredit(credit);
        r.setDate(new Date());
        r.setMontant(dto.getMontant());
        r.setType(dto.getType());
        return mapper.toRemboursementDTO(remboursementRepository.save(r));
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        return remboursementRepository.findByCreditId(creditId)
                .stream().map(mapper::toRemboursementDTO).toList();
    }
}
