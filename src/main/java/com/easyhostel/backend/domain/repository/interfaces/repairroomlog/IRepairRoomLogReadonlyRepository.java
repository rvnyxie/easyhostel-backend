package com.easyhostel.backend.domain.repository.interfaces.repairroomlog;

import com.easyhostel.backend.domain.entity.RepairRoomLog;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RepairRoomLog related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRepairRoomLogReadonlyRepository extends IBaseReadonlyRepository<RepairRoomLog, String> {
}
