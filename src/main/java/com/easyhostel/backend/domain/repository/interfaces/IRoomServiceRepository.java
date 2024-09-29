package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.RoomService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoomServiceRepository extends JpaRepository<RoomService, UUID> {
}
