package com.easyhostel.backend.application.service.interfaces.contractroomamenity;

import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;

/**
 * Interface for ContractRoomAmenity service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IContractRoomAmenityService extends IBaseService<ContractRoomAmenityDto, ContractRoomAmenityCreationDto, ContractRoomAmenityUpdateDto, ContractRoomAmenityId> {
}
