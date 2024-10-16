package com.easyhostel.backend.domain.repository.interfaces.managerhouse;

import com.easyhostel.backend.domain.entity.ManagerHouse;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ManagerHouse related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IManagerHouseRepository extends IBaseRepository<ManagerHouse, ManagerHouseId> {
}
