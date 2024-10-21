package com.easyhostel.backend.application.service.implementations.rentpayment;

import com.easyhostel.backend.application.dto.rentpayment.RentPaymentCreationDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRentPaymentMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.rentpayment.IRentPaymentService;
import com.easyhostel.backend.domain.entity.RentPayment;
import com.easyhostel.backend.domain.repository.interfaces.rentpayment.IRentPaymentRepository;
import com.easyhostel.backend.domain.service.interfaces.rentpayment.IRentPaymentBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * RentPayment modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RentPaymentService extends BaseService<RentPayment, RentPaymentDto, RentPaymentCreationDto, RentPaymentUpdateDto, String> implements IRentPaymentService {

    private final IRentPaymentRepository _rentPaymentRepository;
    private final IRentPaymentBusinessValidator _rentPaymentBusinessValidator;
    private final IRentPaymentMapper _rentPaymentMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RentPaymentService(IRentPaymentRepository rentPaymentRepository,
                              IRentPaymentBusinessValidator rentPaymentBusinessValidator,
                              IRentPaymentMapper rentPaymentMapper,
                              DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(rentPaymentRepository, taskExecutor);
        _rentPaymentRepository = rentPaymentRepository;
        _rentPaymentBusinessValidator = rentPaymentBusinessValidator;
        _rentPaymentMapper = rentPaymentMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public RentPayment mapCreationDtoToEntity(RentPaymentCreationDto rentPaymentCreationDto) {
        var rentPayment = _rentPaymentMapper.MAPPER.mapRentPaymentCreationDtoToRentPayment(rentPaymentCreationDto);

        return rentPayment;
    }

    @Override
    public RentPayment mapUpdateDtoToEntity(RentPaymentUpdateDto rentPaymentUpdateDto) {
        var rentPayment = _rentPaymentMapper.MAPPER.mapRentPaymentUpdateDtoToRentPayment(rentPaymentUpdateDto);

        return rentPayment;
    }

    @Override
    public RentPaymentDto mapEntityToDto(RentPayment rentPayment) {
        var rentPaymentDto = _rentPaymentMapper.mapRentPaymentToRentPaymentDto(rentPayment);

        return rentPaymentDto;
    }

    // TODO: Add business creation validation for RentPayment
    @Override
    public CompletableFuture<Void> validateCreationBusiness(RentPaymentCreationDto rentPaymentCreationDto) {
        return super.validateCreationBusiness(rentPaymentCreationDto);
    }

    // TODO: Add business update validation for RentPayment
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RentPaymentUpdateDto rentPaymentUpdateDto) {
        return super.validateUpdateBusiness(rentPaymentUpdateDto);
    }
}
