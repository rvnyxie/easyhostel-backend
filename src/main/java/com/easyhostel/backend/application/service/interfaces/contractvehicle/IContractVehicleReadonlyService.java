package com.easyhostel.backend.application.service.interfaces.contractvehicle;

import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;

/**
 * Interface for ContractVehicle service, extends base GET methods
 *
 * @author Nyx
 */
public interface IContractVehicleReadonlyService extends IBaseReadonlyService<ContractVehicleDto, ContractVehicleId> {
}
