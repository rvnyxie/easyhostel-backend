package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.ContractRoomService;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomServiceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractRoomServiceRepository extends JpaRepository<ContractRoomService, ContractRoomServiceId> {
}
