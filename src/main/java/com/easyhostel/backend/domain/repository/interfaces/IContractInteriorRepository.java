package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.ContractInterior;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractInteriorRepository extends JpaRepository<ContractInterior, ContractInteriorId> {
}
