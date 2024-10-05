package com.easyhostel.backend.application.dto.rentpayment;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for update RentPayment entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RentPaymentUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.rentPaymentId.notBlank}")
    private String rentPaymentId;

    private Boolean isPaid;

    @NotNull(message = "{validation.totalPrice.notNull}")
    @Min(value = 0, message = "{validation.totalPrice.notNegative}")
    private Float totalPrice;

    private String note;

}
