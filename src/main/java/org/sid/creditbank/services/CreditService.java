package org.sid.creditbank.services;

import org.sid.creditbank.dtos.*;
import org.sid.creditbank.enums.StatutCredit;
import org.sid.creditbank.exceptions.ClientNotFoundException;
import org.sid.creditbank.exceptions.CreditNotFoundException;
import java.util.List;


public interface CreditService {

    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);
    ClientDTO getClient(Long id) throws ClientNotFoundException;
    List<ClientDTO> listClients();
    List<ClientDTO> searchClients(String keyword);


    CreditDTO getCredit(Long id) throws CreditNotFoundException;
    List<CreditDTO> listCredits();
    List<CreditDTO> getCreditsByClient(Long clientId);
    CreditDTO saveCreditPersonnel(CreditPersonnelDTO dto) throws ClientNotFoundException;
    CreditDTO saveCreditImmobilier(CreditImmobilierDTO dto) throws ClientNotFoundException;
    CreditDTO saveCreditProfessionnel(CreditProfessionnelDTO dto) throws ClientNotFoundException;
    CreditDTO updateStatut(Long creditId, StatutCredit statut) throws CreditNotFoundException;
    void deleteCredit(Long id);


    RemboursementDTO saveRemboursement(RemboursementDTO dto) throws CreditNotFoundException;
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);
}
