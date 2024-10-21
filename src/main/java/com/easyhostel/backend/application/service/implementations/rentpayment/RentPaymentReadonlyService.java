package com.easyhostel.backend.application.service.implementations.rentpayment;

import com.easyhostel.backend.application.dto.rentpayment.RentPaymentDto;
import com.easyhostel.backend.application.mapping.interfaces.IRentPaymentMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.rentpayment.IRentPaymentReadonlyService;
import com.easyhostel.backend.domain.entity.RentPayment;
import com.easyhostel.backend.domain.repository.interfaces.rentpayment.IRentPaymentReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * RentPayment readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RentPaymentReadonlyService extends BaseReadonlyService<RentPayment, RentPaymentDto, String> implements IRentPaymentReadonlyService {

    private final IRentPaymentMapper _rentPaymentMapper;

    public RentPaymentReadonlyService(IRentPaymentReadonlyRepository rentPaymentReadonlyRepository,
                                      IRentPaymentMapper rentPaymentMapper,
                                      DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(rentPaymentReadonlyRepository, taskExecutor);
        _rentPaymentMapper = rentPaymentMapper;
    }

    @Override
    public RentPaymentDto mapEntityToDto(RentPayment rentPayment) {
        var rentPaymentDto = _rentPaymentMapper.MAPPER.mapRentPaymentToRentPaymentDto(rentPayment);

        return rentPaymentDto;
    }
}
