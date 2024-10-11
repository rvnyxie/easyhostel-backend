package com.easyhostel.backend.domain.repository.interfaces.contractvehicle;

import com.easyhostel.backend.domain.entity.ContractVehicle;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ContractVehicle related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IContractVehicleReadonlyRepository extends IBaseReadonlyRepository<ContractVehicle, ContractVehicleId> {
}
