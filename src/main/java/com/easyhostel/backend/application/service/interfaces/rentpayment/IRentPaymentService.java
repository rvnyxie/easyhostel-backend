package com.easyhostel.backend.application.service.interfaces.rentpayment;

import com.easyhostel.backend.application.dto.rentpayment.RentPaymentCreationDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for RentPayment service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRentPaymentService extends IBaseService<RentPaymentDto, RentPaymentCreationDto, RentPaymentUpdateDto, String> {
}
