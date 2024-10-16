package com.easyhostel.backend.domain.service.implementation.managerhouse;

import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.managerhouse.IManagerHouseRepository;
import com.easyhostel.backend.domain.service.interfaces.house.IHouseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.manager.IManagerBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.managerhouse.IManagerHouseBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for ManagerHouse's business validator
 *
 * @author Nyx
 */
@Service
public class ManagerHouseBusinessValidator implements IManagerHouseBusinessValidator {

    private final IManagerHouseRepository _managerHouseRepository;
    private final IManagerBusinessValidator _managerBusinessValidator;
    private final IHouseBusinessValidator _houseBusinessValidator;

    public ManagerHouseBusinessValidator(IManagerHouseRepository managerHouseRepository,
                                         IManagerBusinessValidator managerBusinessValidator,
                                         IHouseBusinessValidator houseBusinessValidator) {
        _managerHouseRepository = managerHouseRepository;
        _managerBusinessValidator = managerBusinessValidator;
        _houseBusinessValidator = houseBusinessValidator;
    }

    @Override
    public void checkIfManagerAndHouseExistedByIds(String managerId, String houseId) {
        _managerBusinessValidator.checkIfManagerExistedById(managerId);
        _houseBusinessValidator.checkIfHouseExistedFromId(houseId);
    }

    @Override
    public void checkIfManagerHouseExistedById(ManagerHouseId managerHouseId) {
        var isManagerHouseExisted = _managerHouseRepository.existsById(managerHouseId);

        if (!isManagerHouseExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.managerHouse.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfManagerHouseNotExistedById(ManagerHouseId managerHouseId) {
        var isManagerHouseExisted = _managerHouseRepository.existsById(managerHouseId);

        if (isManagerHouseExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.managerHouse.alreadyExisted"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
