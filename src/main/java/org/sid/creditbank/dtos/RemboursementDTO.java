package org.sid.creditbank.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.creditbank.enums.TypeRemboursement;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class RemboursementDTO {
    private Long id;
    private Date date;
    private double montant;
    private TypeRemboursement type;
    private Long creditId;
}
