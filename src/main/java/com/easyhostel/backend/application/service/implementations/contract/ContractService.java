package com.easyhostel.backend.application.service.implementations.contract;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.contract.IContractService;
import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Contract modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractService extends BaseService<Contract, ContractDto, ContractCreationDto, ContractUpdateDto, String> implements IContractService {

    private final IContractRepository _contractRepository;
    private final IContractBusinessValidator _contractBusinessValidator;
    private final IContractMapper _contractMapper;

    public ContractService(IBaseRepository<Contract, String> baseRepository, IContractRepository contractRepository, IContractBusinessValidator contractBusinessValidator, IContractMapper contractMapper) {
        super(baseRepository);
        _contractRepository = contractRepository;
        _contractBusinessValidator = contractBusinessValidator;
        _contractMapper = contractMapper;
    }

    @Override
    public Contract mapCreationDtoToEntity(ContractCreationDto contractCreationDto) {
        var contract = _contractMapper.mapContractCreationDtoToContract(contractCreationDto);

        return contract;
    }

    @Override
    public Contract mapUpdateDtoToEntity(ContractUpdateDto contractUpdateDto) {
        var contract = _contractMapper.mapContractUpdateDtoToContract(contractUpdateDto);

        return contract;
    }

    @Override
    public ContractDto mapEntityToDto(Contract contract) {
        var contractDto = _contractMapper.mapContractToContractDto(contract);

        return contractDto;
    }

    // TODO: Add business creation validation for Contract
    @Override
    public CompletableFuture<Void> validateCreationBusiness(ContractCreationDto contractCreationDto) {
        return super.validateCreationBusiness(contractCreationDto);
    }

    // TODO: Add business update validation for Contract
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(ContractUpdateDto contractUpdateDto) {
        return super.validateUpdateBusiness(contractUpdateDto);
    }

}
