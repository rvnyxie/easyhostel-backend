package com.easyhostel.backend.domain.repository.interfaces.contract;

import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Contract related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IContractRepository extends IBaseRepository<Contract, String> {
}
