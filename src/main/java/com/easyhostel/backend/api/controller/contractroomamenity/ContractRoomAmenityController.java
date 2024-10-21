package com.easyhostel.backend.api.controller.contractroomamenity;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractroomamenity.IContractRoomAmenityService;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * Asynchronously delete a ContractRoomAmenity by IDs
     *
     * @param contractId Contract's ID
     * @param roomAmenityId RoomAmenity's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{contractId}/{roomAmenityId}")
    public ResponseEntity<FormattedResponse<Void>> deleteContractRoomAmenityByIdsAsync(
            @PathVariable String contractId,
            @PathVariable String roomAmenityId
    ) {
        _contractRoomAmenityService.deleteContractRoomAmenityByIdsAsync(contractId, roomAmenityId).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale(""),
                null
        );
        return ResponseEntity.ok(response);
    }
}
