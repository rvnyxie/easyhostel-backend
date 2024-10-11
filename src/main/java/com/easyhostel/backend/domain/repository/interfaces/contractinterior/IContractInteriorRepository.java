package com.easyhostel.backend.domain.repository.interfaces.contractinterior;

import com.easyhostel.backend.domain.entity.ContractInterior;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ContractInterior related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IContractInteriorRepository extends IBaseRepository<ContractInterior, ContractInteriorId> {
}
