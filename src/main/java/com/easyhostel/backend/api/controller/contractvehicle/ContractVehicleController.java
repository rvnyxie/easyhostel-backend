package com.easyhostel.backend.api.controller.contractvehicle;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleCreationDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractvehicle.IContractVehicleService;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
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
 * Controller for ContractVehicle
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/contracts-vehicles")
@Tag(name = "ContractVehicle Controller")
public class ContractVehicleController extends BaseController<ContractVehicleDto, ContractVehicleCreationDto, ContractVehicleUpdateDto, ContractVehicleId> {

    private final IContractVehicleService _contractVehicleService;

    public ContractVehicleController(@Qualifier("contractVehicleService") IContractVehicleService contractVehicleService) {
        super(contractVehicleService);
        _contractVehicleService = contractVehicleService;
    }

    /**
     * Asynchronously get ContractVehicle by IDs
     *
     * @param contractId Contract's ID
     * @param vehicleId Vehicle's ID
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{contractId}/{vehicleId}")
    public ResponseEntity<FormattedResponse<ContractVehicleDto>> getContractInteriorByIdsAsync(@PathVariable @Valid String contractId,
                                                                                                @PathVariable @Valid String vehicleId) {
        var contractVehicleId = ContractVehicleId.builder().contractId(contractId).vehicleId(vehicleId).build();

        var dtoEntity = _contractVehicleService.getByIdAsync(contractVehicleId).join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("get.contractVehicle.success"),
                dtoEntity
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Asynchronously delete a ContractVehicle by IDs
     *
     * @param contractId Contract's ID
     * @param vehicleId Vehicle's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{contractId}/{vehicleId}")
    public ResponseEntity<FormattedResponse<Void>> deleteContractVehicleByIdsAsync(@PathVariable @Valid String contractId,
                                                                                   @PathVariable @Valid String vehicleId) {
        var contractVehicleIdToDelete = ContractVehicleId.builder().contractId(contractId).vehicleId(vehicleId).build();

        _contractVehicleService.deleteContractVehicleByIdsAsync(contractVehicleIdToDelete).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("delete.contractVehicle.success"),
                null
        );

        return ResponseEntity.ok(response);
    }

    @Override
    @Hidden
    public ResponseEntity<FormattedResponse<ContractVehicleDto>> getByIdAsync(ContractVehicleId contractVehicleId) {
        return super.getByIdAsync(contractVehicleId);
    }

    @Override
    @Hidden
    public ResponseEntity<FormattedResponse<Void>> deleteByIdAsync(ContractVehicleId contractVehicleId) {
        return super.deleteByIdAsync(contractVehicleId);
    }

}
