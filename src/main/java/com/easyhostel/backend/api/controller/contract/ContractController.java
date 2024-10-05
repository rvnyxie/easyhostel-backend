package com.easyhostel.backend.api.controller.contract;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.service.interfaces.contract.IContractService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Manager
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/contract")
@Tag(name = "Contract Controller")
public class ContractController extends BaseController<ContractDto, ContractCreationDto, ContractUpdateDto, String> {

    private final IContractService _contractService;

    public ContractController(@Qualifier("contractService") IContractService contractService) {
        super(contractService);
        _contractService = contractService;
    }

}
