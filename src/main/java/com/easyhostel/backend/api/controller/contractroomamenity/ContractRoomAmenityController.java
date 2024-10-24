package com.easyhostel.backend.api.controller.contractroomamenity;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractroomamenity.IContractRoomAmenityService;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Asynchronously get ContractRoomAmenity by IDs
     *
     * @param contractId Contract's ID
     * @param roomAmenityId RoomAmenity's ID
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{contractId}/{roomAmenityId}")
    public ResponseEntity<FormattedResponse<ContractRoomAmenityDto>> getContractRoomAmenityByIdsAsync(@PathVariable @Valid String contractId,
                                                                                                      @PathVariable @Valid String roomAmenityId) {
        var contractRoomAmenityId = ContractRoomAmenityId.builder().contractId(contractId).roomAmenityId(roomAmenityId).build();

        var dtoEntity = _contractRoomAmenityService.getByIdAsync(contractRoomAmenityId).join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("get.contractRoomAmenity.success"),
                dtoEntity
        );
        return ResponseEntity.ok(response);
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
        var contractRoomAmenityId = ContractRoomAmenityId.builder()
                .contractId(contractId)
                .roomAmenityId(roomAmenityId)
                .build();

        _contractRoomAmenityService.deleteContractRoomAmenityByIdsAsync(contractRoomAmenityId).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("delete.contractRoomAmenity.success"),
                null
        );
        return ResponseEntity.ok(response);
    }

    @Override
    @Hidden
    public ResponseEntity<FormattedResponse<ContractRoomAmenityDto>> getByIdAsync(ContractRoomAmenityId contractRoomAmenityId) {
        return super.getByIdAsync(contractRoomAmenityId);
    }

    @Override
    @Hidden
    public ResponseEntity<FormattedResponse<Void>> deleteByIdAsync(ContractRoomAmenityId contractRoomAmenityId) {
        return super.deleteByIdAsync(contractRoomAmenityId);
    }

}
