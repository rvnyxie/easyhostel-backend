package com.easyhostel.backend.application.dto.rentpayment;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for RentPayment entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RentPaymentDto extends BaseDtoEntity {

    private String rentPaymentId;

    private Boolean isPaid;

    private float totalPrice;

    private String note;

}
