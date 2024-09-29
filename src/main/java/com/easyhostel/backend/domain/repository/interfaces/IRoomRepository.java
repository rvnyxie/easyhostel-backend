package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoomRepository extends JpaRepository<Room, UUID> {
}
