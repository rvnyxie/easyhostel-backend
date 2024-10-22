package com.easyhostel.backend.domain.service.interfaces.interior;

import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Interior's business validator
 *
 * @author Nyx
 */
public interface IInteriorBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check is Interior existed by ID
     *
     * @param interiorId Interior's ID
     * @exception EntityNotFoundException If Interior not found
     * @author Nyx
     */
    void checkIfInteriorExistedById(String interiorId);

    /**
     * Check if new Interior has duplicated Interior's name
     *
     * @param interiorName Interior's name
     * @exception DuplicatedDistinctRequiredValueException Interior's name already existed
     * @author Nyx
     */
    void checkIfNewInteriorHasDuplicatedName(String interiorName);

    /**
     * Check if update Interior which change Permission's name to existed value
     *
     * @param interiorId Interior's ID
     * @param updateInteriorName Interior's name
     * @exception DuplicatedDistinctRequiredValueException Interior's name already existed
     * @author Nyx
     */
    void checkIfUpdateInteriorHasDuplicatedName(String interiorId, String updateInteriorName);

    /**
     * Check if Interior's name existed
     *
     * @param interiorName Interior's name
     * @exception DuplicatedDistinctRequiredValueException Interior's name already existed
     * @author Nyx
     */
    void checkIfInteriorNameExistedThrowException(String interiorName);

}
