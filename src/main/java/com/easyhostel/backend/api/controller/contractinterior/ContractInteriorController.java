package com.easyhostel.backend.api.controller.contractinterior;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorCreationDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contractinterior.IContractInteriorService;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.response.FormattedResponse;
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
        _contractInteriorService.deleteContractInteriorByIdsAsync(contractId, interiorId);

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
