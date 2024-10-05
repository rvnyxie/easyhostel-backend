package com.easyhostel.backend.domain.repository.interfaces.contract;

import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Contract related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IContractReadonlyRepository extends IBaseReadonlyRepository<Contract, String> {
}
