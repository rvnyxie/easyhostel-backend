package com.easyhostel.backend.api.controller.contractinterior;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorCreationDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractinterior.IContractInteriorService;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for ContractInterior
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/contracts-interiors")
@Tag(name = "ContractInterior Controller")
public class ContractInteriorController extends BaseController<ContractInteriorDto, ContractInteriorCreationDto, ContractInteriorUpdateDto, ContractInteriorId> {

    private final IContractInteriorService _contractInteriorService;

    public ContractInteriorController(@Qualifier("contractInteriorService") IContractInteriorService contractInteriorService) {
        super(contractInteriorService);
        _contractInteriorService = contractInteriorService;
    }

    /**
     * Asynchronously get ContractInterior by IDs
     *
     * @param contractId Contract's ID
     * @param interiorId Interior's ID
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{contractId}/{interiorId}")
    public ResponseEntity<FormattedResponse<ContractInteriorDto>> getContractInteriorByIdsAsync(@PathVariable @Valid String contractId,
                                                                                                @PathVariable @Valid String interiorId) {
        var contractInteriorId = ContractInteriorId.builder().contractId(contractId).interiorId(interiorId).build();

        var dtoEntity = _contractInteriorService.getByIdAsync(contractInteriorId).join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("get.contractInterior.success"),
                dtoEntity
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Asynchronously delete a ContractInterior by IDs
     *
     * @param contractId Contract's ID
     * @param interiorId Interior's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{contractId}/{interiorId}")
    public ResponseEntity<FormattedResponse<Void>> deleteContractInteriorByIdsAsync(@PathVariable @Valid String contractId,
                                                                                    @PathVariable @Valid String interiorId) {
        var contractInteriorId = ContractInteriorId.builder()
                .contractId(contractId)
                .interiorId(interiorId)
                .build();
        _contractInteriorService.deleteContractInteriorByIdsAsync(contractInteriorId).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("delete.contractInterior.success"),
                null
        );

        return ResponseEntity.ok(response);
    }

}
