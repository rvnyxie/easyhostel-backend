package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.RoomService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomServiceRepository extends JpaRepository<RoomService, String> {
}
