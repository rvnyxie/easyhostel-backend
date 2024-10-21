package com.easyhostel.backend.domain.service.implementation.house;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.house.IHouseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for House's business validator
 *
 * @author Nyx
 */
@Service
public class HouseBusinessValidator extends BaseBusinessValidator implements IHouseBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IHouseRepository _houseRepository;
    private final IRoomBusinessValidator _roomBusinessValidator;

    public HouseBusinessValidator(IAuthenticationService authenticationService,
                                  IHouseRepository houseRepository,
                                  IRoomBusinessValidator roomBusinessValidator) {
        super(authenticationService);
        _houseRepository = houseRepository;
        _authenticationService = authenticationService;
        _roomBusinessValidator = roomBusinessValidator;
    }

    @Override
    public House checkIfHouseExistedFromId(String houseId) {
            var house = _houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException(
                    Translator.toLocale("exception.house.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            ));
            return house;
    }

    @Override
    public void checkIsRoomBelongedToHouse(String houseId, String roomId) {
        var house = checkIfHouseExistedFromId(houseId);
        _roomBusinessValidator.checkIfRoomExistedById(roomId);

        var isRoomBelongedToHouse = house.getRooms().stream()
                .anyMatch(room -> room.getRoomId().equals(roomId));

        if (!isRoomBelongedToHouse) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.roomBelongedToHouse.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        };
    }

    @Override
    public void checkIfHouseSupervisedByAuthUser(String houseId) {
        var currentAuthenticatedUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var isHouseSupervisedByManager = currentAuthenticatedUser.getManagerHouses()
                .stream()
                .anyMatch(managerHouse -> managerHouse.getManagerHouseId().getHouseId().equals(houseId));

        if (!isHouseSupervisedByManager) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.houseNotSupervisedByManager"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }
}
