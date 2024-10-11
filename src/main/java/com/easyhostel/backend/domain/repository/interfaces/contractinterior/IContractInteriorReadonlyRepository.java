package com.easyhostel.backend.domain.repository.interfaces.contractinterior;

import com.easyhostel.backend.domain.entity.ContractInterior;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ContractInterior related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IContractInteriorReadonlyRepository extends IBaseReadonlyRepository<ContractInterior, ContractInteriorId> {
}
