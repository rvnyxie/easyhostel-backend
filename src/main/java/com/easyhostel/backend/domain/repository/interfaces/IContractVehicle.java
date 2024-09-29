package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.ContractVehicle;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractVehicle extends JpaRepository<ContractVehicle, ContractVehicleId> {
}
