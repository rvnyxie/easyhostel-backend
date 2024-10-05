package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractRoomAmenityRepository extends JpaRepository<ContractRoomAmenity, ContractRoomAmenityId> {
}
