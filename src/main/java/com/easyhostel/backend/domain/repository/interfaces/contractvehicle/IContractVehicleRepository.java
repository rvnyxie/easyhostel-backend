package com.easyhostel.backend.domain.repository.interfaces.contractvehicle;

import com.easyhostel.backend.domain.entity.ContractVehicle;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ContractVehicle related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IContractVehicleRepository extends IBaseRepository<ContractVehicle, ContractVehicleId> {
}
