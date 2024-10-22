package com.easyhostel.backend.domain.service.implementation.contract;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.MissingRequiredFieldsException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for Contract's business validator
 *
 * @author Nyx
 */
@Service
public class ContractBusinessValidator extends BaseBusinessValidator implements IContractBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IContractRepository _contractRepository;

    public ContractBusinessValidator(IAuthenticationService authenticationService,
                                     IContractRepository contractRepository) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _contractRepository = contractRepository;
    }


    @Override
    public void checkIfContractExistedById(String contractId) {
        var isContractExisted = _contractRepository.existsById(contractId);

        if (!isContractExisted) {
            throw  new EntityNotFoundException(
                    Translator.toLocale("exception.contract.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfContractManageableByAuthUser(String contractId) {
        var currentAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var contract = _contractRepository.findById(contractId).orElseThrow();

        var houseWhichContractBelonged = contract.getRoom().getHouse();

        var isContractManageableByAuthUser = currentAuthUser.getManagerHouses()
                .stream()
                .anyMatch(managerHouse -> managerHouse.getManagerHouseId().getHouseId().equals(houseWhichContractBelonged.getHouseId()));

        if (!isContractManageableByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.contractNotManageableByManager"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Override
    public void checkIfRoomIdProvidedInContractCreationDto(ContractCreationDto contractCreationDto) {
        var room = contractCreationDto.getRoom();

        if (room == null) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.room.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }

        if (room.getRoomId() == null) {
            throw new MissingRequiredFieldsException(
                    Translator.toLocale("exception.roomId.notFound"),
                    ErrorCode.MISSING_FIELDS,
                    HttpStatus.BAD_REQUEST
            );
        }
    }


}
