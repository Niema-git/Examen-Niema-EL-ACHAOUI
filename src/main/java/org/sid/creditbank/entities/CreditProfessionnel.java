package org.sid.creditbank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CPR")
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}