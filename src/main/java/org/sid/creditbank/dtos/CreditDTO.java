package org.sid.creditbank.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.creditbank.enums.StatutCredit;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditDTO {
    private Long id;
    private Date dateDemande;
    private StatutCredit statut;
    private Date dateAcception;
    private double montant;
    private int duree;
    private double tauxInteret;
    private Long clientId;
    private String clientName;
    private String type;
}