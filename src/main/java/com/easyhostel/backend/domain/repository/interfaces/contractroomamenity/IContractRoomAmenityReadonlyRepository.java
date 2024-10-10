package com.easyhostel.backend.domain.repository.interfaces.contractroomamenity;

import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ContractRoomAmenity related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IContractRoomAmenityReadonlyRepository extends IBaseReadonlyRepository<ContractRoomAmenity, ContractRoomAmenityId> {
}
