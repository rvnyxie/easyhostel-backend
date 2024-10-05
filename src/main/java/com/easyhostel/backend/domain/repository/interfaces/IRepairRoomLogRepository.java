package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.RepairRoomLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepairRoomLogRepository extends JpaRepository<RepairRoomLog, String> {
}
