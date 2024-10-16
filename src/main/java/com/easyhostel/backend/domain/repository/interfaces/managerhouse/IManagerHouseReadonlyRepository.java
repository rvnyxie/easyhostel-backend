package com.easyhostel.backend.domain.repository.interfaces.managerhouse;

import com.easyhostel.backend.domain.entity.ManagerHouse;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ManagerHouse related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IManagerHouseReadonlyRepository extends IBaseReadonlyRepository<ManagerHouse, ManagerHouseId> {
}
