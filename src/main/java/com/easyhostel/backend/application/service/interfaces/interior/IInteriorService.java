package com.easyhostel.backend.application.service.interfaces.interior;

import com.easyhostel.backend.application.dto.interior.InteriorCreationDto;
import com.easyhostel.backend.application.dto.interior.InteriorDto;
import com.easyhostel.backend.application.dto.interior.InteriorUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for Interior service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IInteriorService extends IBaseService<InteriorDto, InteriorCreationDto, InteriorUpdateDto, String> {
}
