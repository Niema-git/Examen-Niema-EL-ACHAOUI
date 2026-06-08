package org.sid.creditbank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.creditbank.enums.TypeBien;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBien;
}