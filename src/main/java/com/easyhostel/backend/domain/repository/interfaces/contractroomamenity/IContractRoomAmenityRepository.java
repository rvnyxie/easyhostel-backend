package com.easyhostel.backend.domain.repository.interfaces.contractroomamenity;

import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ContractRoomAmenity related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IContractRoomAmenityRepository extends IBaseRepository<ContractRoomAmenity, ContractRoomAmenityId> {
}
