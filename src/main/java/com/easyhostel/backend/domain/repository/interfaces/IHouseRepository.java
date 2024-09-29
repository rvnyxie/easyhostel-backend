package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHouseRepository extends JpaRepository<House, UUID> {
}
