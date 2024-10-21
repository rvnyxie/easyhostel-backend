package com.easyhostel.backend.api.controller.contractvehicle;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleCreationDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractvehicle.IContractVehicleService;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        _contractVehicleService.deleteContractVehicleByIdsAsync(contractId, vehicleId).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("delete.contractVehicle.success"),
                null
        );

        return ResponseEntity.ok(response);
    }

}
