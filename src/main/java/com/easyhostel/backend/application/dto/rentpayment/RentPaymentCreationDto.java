package com.easyhostel.backend.application.dto.rentpayment;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating RentPayment entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RentPaymentCreationDto extends BaseDtoEntity {

    @NotNull(message = "{validation.isPaid.notNull}")
    private Boolean isPaid;

    @NotNull(message = "{validation.totalPrice.notNull}")
    @Min(value = 0, message = "{validation.totalPrice.notNegative}")
    private Float totalPrice;

    private String note;

}
