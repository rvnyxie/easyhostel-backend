package com.easyhostel.backend.application.service.interfaces.contractinterior;

import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;

/**
 * Interface for ContractInterior service, extends base GET methods
 *
 * @author Nyx
 */
public interface IContractInteriorReadonlyService extends IBaseReadonlyService<ContractInteriorDto, ContractInteriorId> {
}
