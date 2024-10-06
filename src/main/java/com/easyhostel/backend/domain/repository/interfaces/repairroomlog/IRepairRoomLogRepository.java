package com.easyhostel.backend.domain.repository.interfaces.repairroomlog;

import com.easyhostel.backend.domain.entity.RepairRoomLog;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RepairRoomLog related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRepairRoomLogRepository extends IBaseRepository<RepairRoomLog, String> {
}
