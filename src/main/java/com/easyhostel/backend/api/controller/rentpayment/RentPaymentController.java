package com.easyhostel.backend.api.controller.rentpayment;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentCreationDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentUpdateDto;
import com.easyhostel.backend.application.service.interfaces.rentpayment.IRentPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for RentPayment
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/rent-payments")
@Tag(name = "RentPayment Controller")
public class RentPaymentController extends BaseController<RentPaymentDto, RentPaymentCreationDto, RentPaymentUpdateDto, String> {

    private final IRentPaymentService _rentPaymentService;

    public RentPaymentController(IRentPaymentService rentPaymentService) {
        super(rentPaymentService);
        _rentPaymentService = rentPaymentService;
    }

}
