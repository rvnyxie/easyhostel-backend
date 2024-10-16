package com.easyhostel.backend.domain.service.interfaces.managerhouse;

import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;

/**
 * Interface for defining ManagerHouse's business validator
 *
 * @author Nyx
 */
public interface IManagerHouseBusinessValidator {

    /**
     * Check if Manager and House existed by IDs
     *
     * @param managerId Manager's ID
     * @param houseId House's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Manager or House not found
     * @author Nyx
     */
    void checkIfManagerAndHouseExistedByIds(String managerId, String houseId);

    /**
     * Check if ManagerHouse existed by ID
     *
     * @param managerHouseId ManagerHouseId Object
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If ManagerHouse not found
     * @author Nyx
     */
    void checkIfManagerHouseExistedById(ManagerHouseId managerHouseId);

    /**
     * Check if ManagerHouse not existed by ID
     *
     * @param managerHouseId ManagerHouseId Object
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If ManagerHouse not found
     * @author Nyx
     */
    void checkIfManagerHouseNotExistedById(ManagerHouseId managerHouseId);

}
