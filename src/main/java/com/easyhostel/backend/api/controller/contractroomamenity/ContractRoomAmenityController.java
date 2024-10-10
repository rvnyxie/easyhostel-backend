package com.easyhostel.backend.api.controller.contractroomamenity;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractroomamenity.IContractRoomAmenityService;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for ContractRoomAmenity
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/contracts-room-amenities")
@Tag(name = "ContractRoomAmenity Controller")
public class ContractRoomAmenityController extends BaseController<ContractRoomAmenityDto, ContractRoomAmenityCreationDto, ContractRoomAmenityUpdateDto, ContractRoomAmenityId> {

    private final IContractRoomAmenityService _contractRoomAmenityService;

    public ContractRoomAmenityController(@Qualifier("contractRoomAmenityService") IContractRoomAmenityService contractRoomAmenityService) {
        super(contractRoomAmenityService);
        _contractRoomAmenityService = contractRoomAmenityService;
    }
}
